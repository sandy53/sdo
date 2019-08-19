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
    private static final String LOCKED    = "1";
    private static final String UN_LOCKED = "0";

    @Resource
    private RecordService recordService;

    @Resource
    private Operator          operator;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void doSave(Doc doc) throws Exception {

        if (StringUtils.isBlank(doc.getDocId())) {
            //新增
            this.doNew(doc);
            return;
        }
        //校验是否锁定
        this.checkLocked(doc.getDocId());

        if (StringUtils.isNotBlank(doc.getDocId())
                   && StringUtils.isNotBlank(doc.getParent())) {
            //移动
            this.doMove(doc);
            return;
        }
        //修改
        this.doUpdate(doc);
    }

    /**
     * 
     * 
     * @param docId
     * @throws Exception 
     */
    private void checkLocked(String docId) throws Exception {
        Assert.notEmpty(docId);
        //先查文档信息
        RecordOne one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", docId);
        Map<String, Object> doc = recordService.doOne(one);
        if (doc == null || doc.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }
        //
        String currentLock = doc.get("locked").toString();
        if (!StringUtils.equals(currentLock, LOCKED)) {
            //未锁定
            return;
        }
        //用户校验
        int ucode = operator.fetchUserId();
        int docUcode = Integer.parseInt(doc.get("ucode").toString());
        if (ucode != docUcode) {
            throw new Exception("Doc are not yours!!!!!!");
        }
    }

    private void doUpdate(Doc doc) {
        Assert.notEmpty(doc.getDocId());
        Assert.notEmpty(doc.getTitle());

        long updateTime = System.currentTimeMillis();
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", doc.getDocId());
        update.addUpdateField("title", doc.getTitle());
        update.addUpdateField("updateTime", updateTime);
        recordService.doUpdate(update);

        //修改内容
        update = new RecordUpdate(RecordEnum.DocContent);
        update.addCondition("docId", doc.getDocId());
        update.addUpdateField("content", doc.getContent());
        recordService.doUpdate(update);


        //保存日志
        //DocLog 
        DocLog log = new DocLog();
        log.setDocId(doc.getDocId());
        log.setRemarks("修改");
        log.setUcode(operator.fetchUserId());
        log.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);
    }

    /**
     * 移动
     * 
     * @param doc
     */
    private void doMove(Doc doc) {
        Assert.notEmpty(doc.getDocId());
        Assert.notEmpty(doc.getParent());
        if (StringUtils.equals(doc.getDocId(), doc.getParent())) {
            //未移动操作
            return;
        }
        //新父文档ID
        String newParentId = doc.getParent();
        //先查新父类信息
        RecordOne one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", newParentId);
        Map<String, Object> newParentInfo = recordService.doOne(one);
        if (newParentInfo == null || newParentInfo.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }
        //再查当前文档信息
        one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", doc.getDocId());
        Map<String, Object> docInfo = recordService.doOne(one);
        if (docInfo == null || docInfo.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }

        String docParent = docInfo.get("parent").toString(); //当前父文档
        if (StringUtils.equals(newParentId, docParent)) {
            //未移动操作
            return;
        }
        //变更新的父节点为非叶子节点
        this.parentUpdate(newParentId);

        //修改
        long updateTime = System.currentTimeMillis();
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", doc.getDocId());
        update.addUpdateField("parent", newParentId);
        update.addUpdateField("updateTime", updateTime);
        recordService.doUpdate(update);
        //日志
        //保存日志
        //DocLog 
        DocLog log = new DocLog();
        log.setDocId(doc.getDocId());
        log.setRemarks("移动: " + docParent + " -> " + newParentId);
        log.setUcode(operator.fetchUserId());
        log.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);
    }

    private void doNew(Doc doc) {
        Assert.notEmpty(doc.getTitle());
        this.parentUpdate(doc.getParent());
        String docId = getDocId();
        doc.setDocId(docId);
        doc.setLeaf(LEAF);
        //TODO
        long updateTime = System.currentTimeMillis();
        doc.setUcode(operator.fetchUserId());
        doc.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.Doc.name(), Arrays.asList(doc));
        recordService.doSave(recordSave);

        //保存内容
        DocContent docContent = new DocContent();
        docContent.setDocId(docId);
        docContent.setContent(doc.getContent());
        recordSave = new RecordSave(RecordEnum.DocContent.name(), Arrays.asList(docContent));
        recordService.doSave(recordSave);

        //保存日志
        //DocLog 
        DocLog log = new DocLog();
        log.setDocId(docId);
        log.setRemarks("创建");
        log.setUcode(doc.getUcode());
        log.setCreateTime(updateTime);
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
        long updateTime = System.currentTimeMillis();
        //叶子节点要改变为非叶子节点
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", parent);
        update.addUpdateField("leaf", NOT_LEAF);
        update.addUpdateField("updateTime", updateTime);
        recordService.doUpdate(update);

        DocLog log = new DocLog();
        log.setDocId(parent);
        log.setRemarks("变更为父节点");
        log.setUcode(operator.fetchUserId());
        log.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);
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

    @Override
    public void doLock(String docId) throws Exception {
        Assert.notEmpty(docId);
        //先查文档信息
        RecordOne one = new RecordOne(RecordEnum.Doc);
        one.addCondition("docId", docId);
        Map<String, Object> doc = recordService.doOne(one);
        if (doc == null || doc.isEmpty()) {
            throw new RuningException(ResultCode.RECORD_NOT_EXIST);
        }
        //用户校验
        int ucode = operator.fetchUserId();
        int docUcode = Integer.parseInt(doc.get("ucode").toString());
        if (ucode != docUcode) {
            throw new Exception("Doc are not yours!!!!!!");
        }
        long updateTime = System.currentTimeMillis();
        String currentLock = doc.get("locked").toString();
        String locked = StringUtils.equals(currentLock, LOCKED) ? UN_LOCKED : LOCKED;
        RecordUpdate update = new RecordUpdate(RecordEnum.Doc);
        update.addCondition("docId", docId);
        update.addUpdateField("locked", locked);
        update.addUpdateField("updateTime", updateTime);
        recordService.doUpdate(update);

        DocLog log = new DocLog();
        log.setDocId(docId);
        log.setRemarks("锁定: " + locked);
        log.setUcode(operator.fetchUserId());
        log.setCreateTime(updateTime);
        RecordSave recordSave = new RecordSave(RecordEnum.DocLog.name(), Arrays.asList(log));
        recordService.doSave(recordSave);


    }

}
