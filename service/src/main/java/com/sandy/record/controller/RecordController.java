package com.sandy.record.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sandy.common.model.ReqResult;
import com.sandy.record.model.Paging;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.service.RecordService;



@RestController
public class RecordController {
    @Resource
    private RecordService recordService;

    @RequestMapping("/common/{recordCode}/search")
    public ReqResult<RecordQuery> doQuery(@PathVariable("recordCode") String recordCode,
                                          Paging paging) {
        RecordQuery query = new RecordQuery(recordCode, paging);
        recordService.doQuery(query);
        return new ReqResult<RecordQuery>(query);
    }

}
