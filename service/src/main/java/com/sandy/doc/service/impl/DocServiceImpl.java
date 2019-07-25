package com.sandy.doc.service.impl;

import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.auth.core.AbstractAuthInterceptor.Operator;
import com.sandy.common.Assert;
import com.sandy.common.exception.RuningException;
import com.sandy.common.model.ResultCode;
import com.sandy.doc.model.Doc;
import com.sandy.doc.model.DocContent;
import com.sandy.doc.model.DocLog;
import com.sandy.doc.service.DocService;
import com.sandy.record.enums.RecordEnum;
import com.sandy.record.model.RecordOne;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.model.RecordSave;
import com.sandy.record.model.RecordUpdate;
import com.sandy.record.service.RecordService;

/**
 *   文档业务类
 * 
 * @author sandy
 * @version $Id: DocServiceImpl.java, v 0.1 2019年7月23日 下午7:19:50 sandy Exp $
 */
@Service
public class DocServiceImpl implements DocService {

    /** 叶子节点标识*/
    private static final byte LEAF = 1;

    private static final byte NOT_LEAF = 0;


    @Resource
    private RecordService recordService;

    @Resource
    private Operator          operator;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doSave(String title, String content, String parent, String docId) {

        if (StringUtils.isBlank(docId)) {
            //新增
            this.doSave(title, content, parent);
            return;
        }
        //修改
        long updateTime = System.currentTimeMillis();
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", parent);
        update.addUpdateField("title", title);
        update.addUpdateField("updateTime", updateTime);
        recordService.doUpdate(update);

        //修改内容
        update = new RecordUpdate(RecordEnum.DocContent);
        update.addCondition("docId", parent);
        update.addUpdateField("content", content);
        recordService.doUpdate(update);


        //保存日志
        //DocLog 
        DocLog log = new DocLog();
        log.setDocId(docId);
        log.setRemarks("修改");
        log.setUcode(operator.fetchUserId());
        log.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);
    }

    private void doSave(String title, String content, String parent) {
        this.parentUpdate(parent);
        String docId = getDocId();
        Doc doc = new Doc();
        doc.setDocId(docId);
        doc.setTitle(title);
        doc.setLeaf(LEAF);
        doc.setParent(parent);
        //TODO
        doc.setUcode(operator.fetchUserId());
        doc.setCreateTime(System.currentTimeMillis());
        RecordSave recordSave = new RecordSave(RecordEnum.Doc.name(), Arrays.asList(doc));
        recordService.doSave(recordSave);

        //保存内容
        DocContent docContent = new DocContent();
        docContent.setDocId(docId);
        docContent.setContent(content);
        recordSave = new RecordSave(RecordEnum.DocContent.name(), Arrays.asList(docContent));
        recordService.doSave(recordSave);

        //保存日志
        //DocLog 
        DocLog log = new DocLog();
        log.setDocId(docId);
        log.setRemarks("创建");
        log.setUcode(doc.getUcode());
        log.setCreateTime(doc.getCreateTime());
        recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);
    }

    /**
     * 父亲节点更新
     * 
     * @param parent
     */
    private void parentUpdate(String parent) {
        if (StringUtils.isBlank(parent)) {
            return;
        }
        RecordOne one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", parent);
        Map<String, Object> parentInfo = recordService.doOne(one);
        if (parentInfo == null || parentInfo.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }
        Object leafObj = parentInfo.get("leaf");
        if (leafObj == null) {
            return;
        }
        boolean leaf = Boolean.parseBoolean(leafObj.toString());
        if (!leaf) {
            return;
        }
        //叶子节点要改变为非叶子节点
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", parent);
        update.addUpdateField("leaf", NOT_LEAF);
        update.addUpdateField("updateTime", System.currentTimeMillis());
        recordService.doUpdate(update);
    }

    private String getDocId() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    @Override
    public void doQuery(RecordQuery query) {
        recordService.doQuery(query);
    }

    @Override
    public Map<String, Object> doInfo(String docId) {
        Assert.notEmpty(docId);
        RecordOne one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", docId);
        Map<String, Object> doc = recordService.doOne(one);
        if (doc == null || doc.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }

        RecordOne content = new RecordOne(RecordEnum.DocContent);
        content.addCondition("docId", docId);
        Map<String, Object> doOne = recordService.doOne(content);
        doc.put("detail", doOne);
        return doc;
    }

}
