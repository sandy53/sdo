package com.sandy.doc.controller;

import java.util.Arrays;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.auth.core.AbstractAuthInterceptor.Operator;
import com.sandy.common.model.ReqResult;
import com.sandy.common.util.CodeUtils;
import com.sandy.doc.model.Space;
import com.sandy.doc.service.DocService;
import com.sandy.record.enums.RecordEnum;
import com.sandy.record.model.Paging;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.model.RecordSave;
import com.sandy.record.service.RecordService;


/**
 *  文档空间
 * 
 * @author sandy
 * @version $Id: SpaceController.java, v 0.1 2019年9月2日 上午11:10:35 sandy Exp $
 */
@RestController
@RequestMapping("/space")
public class SpaceController {

    @Resource
    private Operator             operator;

    @Resource
    private DocService docService;
    @Resource
    private RecordService recordService;

    @RequestMapping("/list")
    public ReqResult<RecordQuery> doQuery(Paging paging, String parent) {
        RecordQuery query = new RecordQuery(RecordEnum.Space.name(), paging);
        query.setDoPage(false);
        docService.doQuery(query);
        return new ReqResult<>(query);
    }



    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Object doRecord(@RequestParam String name) {
        Space space = new Space();
        space.setName(name);
        space.setCode(CodeUtils.getBySnowflake());
        space.setUcode(operator.fetchUserId());
        space.setCreateTime(System.currentTimeMillis());
        RecordSave save = new RecordSave(RecordEnum.Space.name(), Arrays.asList(space));
        recordService.doSave(save);
        return new ReqResult<>(space);

    }


}
