package com.sandy.doc.service;

import java.util.Map;

import com.sandy.doc.enums.UpdateType;
import com.sandy.doc.model.Doc;
import com.sandy.record.model.RecordQuery;

/**
 *  文档业务接口
 * 
 * @author sandy
 * @version $Id: DocService.java, v 0.1 2019年7月23日 下午7:20:00 sandy Exp $
 */
public interface DocService {

    /**
     *  保存文档
     * 
     * @param title
     * @param content
     * @throws Exception 
     */
    void doSave(UpdateType updateType, Doc doc) throws Exception;

    void doQuery(RecordQuery query);

    Map<String, Object> doInfo(String docId);

    /**
     * 锁定操作   锁定后只有当前用户可以操作
     * 
     * @param docId
     * @throws Exception 
     */
    void doLock(String docId) throws Exception;

}
