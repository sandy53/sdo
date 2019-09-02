package com.sandy.record.service.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.sandy.common.Assert;
import com.sandy.common.exception.RuningException;
import com.sandy.common.model.ResultCode;
import com.sandy.common.redis.RedisUtil;
import com.sandy.common.redis.key.CommonKey;
import com.sandy.record.dao.RecordMapper;
import com.sandy.record.enums.RecordBase;
import com.sandy.record.model.FieldUpdate;
import com.sandy.record.model.RecordField;
import com.sandy.record.model.RecordInfo;
import com.sandy.record.model.RecordOne;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.model.RecordSave;
import com.sandy.record.model.RecordUpdate;
import com.sandy.record.service.RecordService;

import net.sf.json.JSONObject;

/**
 *  记录查询业务处理类
 * 
 * @author sandy
 * @version $Id: RecordServiceImpl.java, v 0.1 2019年1月8日 下午11:56:55 sandy Exp $
 */
@Service
public class RecordServiceImpl implements RecordService {

    private Logger                logger          = LoggerFactory
        .getLogger(RecordServiceImpl.class);

    /** 默认查询字段标识 */
    public static final byte      FIELD_DEFAULT_SEARCH = 1;

    /** mybatis 查询标识*/
    private final static String   QUERY_STATEMENT      = "com.sandy.record.dao.RecordMapper.selectByQuery";

    /** mybatis 查询标识*/
    private final static String   QUERY_ONE_STATEMENT  = "com.sandy.record.dao.RecordMapper.selectOne";

    @Resource
    private SqlSessionTemplate    sqlSessionTemplate;
    @Resource
    private RecordMapper          recordMapper;

    @Resource
    private RedisUtil<RecordInfo> redisUtil;

    @Override
    public void doQuery(RecordQuery query) {
        //TODO 判断空
        RecordInfo recordInfo = queryRecordInfo(query.getRecordCode());
        query.setRecordName(recordInfo.getRecordName());
        buildQueryCondition(query, recordInfo);
        int total = recordMapper.countByQuery(query);
        if (total <= 0) {
            return;
        }
        query.setTotal(total);
        RecordResultHandler handler = new RecordResultHandler();
        sqlSessionTemplate.select(QUERY_STATEMENT, query, handler);
        query.setResults(handler.getResultList());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doSave(RecordSave save) {
        Assert.notNull(save, "record doSave  save is null");
        Assert.notEmpty(save.getRecordCode(), "record doSave  recordCode is empty");
        //获取记录元数据信息
        RecordInfo recordInfo = queryRecordInfo(save.getRecordCode());
        save.setFields(recordInfo.getFields());
        save.setTable(recordInfo.getRecordTable());
        List<RecordBase> records = save.getRecords();
        if (CollectionUtils.isEmpty(records)) {
            //无记录保存
            return;
        }
        //转换数据，以便保存， 
        List<List<String>> values = new ArrayList<List<String>>();
        List<String> value = null;
        JSONObject json = null;
        List<RecordField> fields = recordInfo.getFields();
        List<RecordField> saveFields = new ArrayList<>();
        Object v = null;
        boolean first = true; //第一行需要确定保存的列
        for (RecordBase recordBase : records) {
            json = JSONObject.fromObject(recordBase);
            value = new ArrayList<String>();
            for (RecordField recordField : fields) {
                v = json.get(recordField.getFieldCode());
                if (v == null) {
                    continue;
                }
                if (first) {
                    saveFields.add(recordField);
                }
                value.add(v.toString());//把需要保存的值转换为对应column位置的值
            }
            first = false;
            values.add(value);
        }
        save.setSaveFields(saveFields);
        save.setValues(values);
        int insert = recordMapper.insert(save);
        if (insert < values.size()) {
            logger.error("COMMON-DO-SAVE FAIL: {}, {}", insert, values, recordInfo.getRecordCode());
            throw new RuningException(ResultCode.RECORD_SAVE_FAIL);
        }
    }

    /**
     * 封装查询语句条件
     * 
     * @param recordInfo
     * @return
     */
    private void buildQueryCondition(RecordQuery query, RecordInfo recordInfo) {
        //封装查询 条件
        query.setTable(recordInfo.getRecordTable());
        if (query.getFields() == null) {
            query.setFields(recordInfo.getFields());
        }
        if (CollectionUtils.isEmpty(query.getFields())) { //没有默认查询字段
            throw new RuningException(
                /*ResultCode.NOT_SET_SEARCH_FIELD,*/ recordInfo.getRecordCode());
        }

    }

    /**
     * 加载记录基本信息
     * 
     * @param recordCode
     * @return
     */
    @Override
    public RecordInfo queryRecordInfo(String recordCode) {
        Assert.notEmpty(recordCode);
        Map<String, RecordInfo> infoMap = loadRecordInfo();
        if (infoMap == null || infoMap.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST, recordCode);
        }
        RecordInfo recordInfo = infoMap.get(recordCode);
        if (recordInfo == null) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST, recordCode);
        }
        return recordInfo;
    }

    @Override
    public Collection<RecordInfo> loadRecordInfos() {
        Map<String, RecordInfo> infoMap = loadRecordInfo();
        return infoMap == null ? null : infoMap.values();
    }

    @SuppressWarnings("unchecked")
    private Map<String, RecordInfo> loadRecordInfo() {
        Object object = redisUtil.hash(CommonKey.DICT_RECORD_INFO_KEY);
        Map<String, RecordInfo> map = null;
        if (object != null && object instanceof Map<?, ?>) {
            map = (Map<String, RecordInfo>) object;
            if (!map.isEmpty()) {
                //return map;
            }
        }
        List<RecordInfo> list = recordMapper.selectAllRecordInfo();
        List<String> codes = new ArrayList<String>();
        map = new HashMap<>();
        for (RecordInfo recordInfo : list) {
            map.put(recordInfo.getRecordCode(), recordInfo);
            codes.add(recordInfo.getRecordCode());
        }
        List<RecordField> fieldList = recordMapper.selectAllFieldByCodes(codes);
        RecordInfo record = null;
        for (RecordField field : fieldList) {
            if ((record = map.get(field.getRecordCode())) == null) {
                //不支持的字段 
                continue;
            }
            if (record.getFields() == null) {
                record.setFields(new ArrayList<>());
            }
            record.getFields().add(field);
            //默认查询 
            if (field.getDefSearch() == FIELD_DEFAULT_SEARCH) {
                if (record.getDefFields() == null) {
                    record.setDefFields(new ArrayList<>());
                }
                record.getDefFields().add(field);
            }
            if (record.getFieldMap() == null) {
                record.setFieldMap(new HashMap<String, RecordField>());
            }
            record.getFieldMap().put(field.getFieldCode(), field);
        }
        redisUtil.hashAddAll(CommonKey.DICT_RECORD_INFO_KEY, map);
        return map;

    }

    @Override
    public void doUpdate(RecordUpdate update) {
        Assert.notNull(update);
        List<FieldUpdate> conditionFileds = null;
        List<FieldUpdate> updates = null;
        Assert.notEmpty((conditionFileds = update.getConditions()));
        Assert.notEmpty((updates = update.getUpdateFields()));
        RecordInfo recordInfo = queryRecordInfo(update.getRecordCode());
        update.setTable(recordInfo.getRecordTable());
        //字段列转换
        Map<String, RecordField> fieldMap = recordInfo.getFieldMap();
        RecordField recordField = null;
        for (FieldUpdate fieldUpdate : conditionFileds) {
            if (fieldUpdate.getValue() == null) {
                throw new RuningException("filed value is null " + fieldUpdate.getFieldCode());
            }
            recordField = fieldMap.get(fieldUpdate.getFieldCode());
            if (recordField == null) {
                throw new RuningException("filed not support" + fieldUpdate.getFieldCode());
            }
            fieldUpdate.setFieldColumn(recordField.getFieldColumn());
        }

        for (FieldUpdate fieldUpdate : updates) {
            if (fieldUpdate.getValue() == null) {
                throw new RuningException("filed value is null " + fieldUpdate.getFieldCode());
            }
            recordField = fieldMap.get(fieldUpdate.getFieldCode());
            if (recordField == null) {
                throw new RuningException("filed not support" + fieldUpdate.getFieldCode());
            }
            fieldUpdate.setFieldColumn(recordField.getFieldColumn());
        }

        int u = recordMapper.update(update);
        if (u <= 0) {
            throw new RuningException(ResultCode.RECORD_UPDATE_FAIL, update);
        }
    }

    @Override
    public Map<String, Object> doOne(RecordOne recordOne) {
        Assert.notNull(recordOne);
        Assert.notEmpty(recordOne.getConditions(), "query conditions is empty");
        RecordInfo recordInfo = queryRecordInfo(recordOne.getRecordCode());
        Map<String, RecordField> fieldMap = recordInfo.getFieldMap();
        //
        RecordField field = null;
        for (FieldUpdate fieldUpdate : recordOne.getConditions()) {
            if (fieldUpdate.getValue() == null) {
                throw new RuningException("filed value is null " + fieldUpdate.getFieldCode());
            }
            field = fieldMap.get(fieldUpdate.getFieldCode());
            if (field == null) {
                throw new RuningException("filed not support" + fieldUpdate.getFieldCode());
            }
            fieldUpdate.setFieldColumn(field.getFieldColumn());
        }
        recordOne.setFields(recordInfo.getFields());
        recordOne.setTable(recordInfo.getRecordTable());
        RecordResultHandler handler = new RecordResultHandler();
        sqlSessionTemplate.select(QUERY_ONE_STATEMENT, recordOne, handler);
        System.err.println(handler.getResultList());
        int size = handler.getResultList().size();
        if (size > 1) {
            throw new RuningException("select one but found " + size);
        }
        if (size == 0) {
            return null;
        }
        return handler.getResultList().get(0);

    }

}
