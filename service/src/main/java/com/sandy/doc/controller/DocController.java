package com.sandy.doc.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.auth.core.AbstractAuthInterceptor.Operator;
import com.sandy.common.Assert;
import com.sandy.common.model.ReqResult;
import com.sandy.common.model.ResultCode;
import com.sandy.doc.model.Doc;
import com.sandy.doc.service.DocService;
import com.sandy.record.enums.RecordEnum;
import com.sandy.record.model.Paging;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.model.RecordQuery.Condition;

/**
 *   文档controller
 * 
 * @author sandy
 * @version $Id: DocController.java, v 0.1 2019年7月24日 上午10:03:24 sandy Exp $
 */
@RestController
@RequestMapping("/doc")
public class DocController {
    @Resource
    private Operator             operator;

    @Resource
    private DocService docService;

    @RequestMapping("/list")
    public ReqResult<RecordQuery> doQuery(Paging paging, String parent, String spaceCode) {
        RecordQuery query = new RecordQuery(RecordEnum.Doc.name(), paging);
        List<Condition> conditions = new ArrayList<>();
        Condition condition = new Condition();
        condition.setColumn("parent");
        condition.setValue(parent != null ? parent : "");
        conditions.add(condition);
        condition = new Condition();
        condition.setColumn("space_code");
        condition.setValue(spaceCode != null ? spaceCode : "");
        conditions.add(condition);
        query.setConditions(conditions);
        docService.doQuery(query);

        return new ReqResult<>(query);
    }

    @RequestMapping("/info")
    public Object doInfo(@RequestParam("id") String docId) {
        Map<String, Object> info = docService.doInfo(docId);
        if (info != null) {
            Object ucode = info.get("ucode");
            info.put("owner", operator.fetchUserId() == Integer.parseInt(ucode.toString()));
        }
        return new ReqResult<>(info);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object doRecord(String title, String content, String docId, String parent,
                           String spaceCode) {
        Doc doc = new Doc();
        doc.setTitle(title);
        doc.setContent(content);
        doc.setDocId(docId);
        doc.setParent(parent);
        doc.setSpaceCode(spaceCode);
        try {
            docService.doSave(doc);
            return new ReqResult<>(doc);
        } catch (Exception e) {
            return new ReqResult<>(ResultCode.FIELD, e.getMessage());
        }
    }

    @RequestMapping(value = "/lock", method = RequestMethod.POST)
    public Object doLock(@RequestParam String docId) {
        Assert.notEmpty(docId);
        try {
            docService.doLock(docId);
            return new ReqResult<>();
        } catch (Exception e) {
            return new ReqResult<>(ResultCode.FIELD, e.getMessage());
        }
    }
}
