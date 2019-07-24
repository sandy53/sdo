package com.sandy.record.service;

import java.util.Collection;
import java.util.Map;

import com.sandy.record.model.RecordInfo;
import com.sandy.record.model.RecordOne;
import com.sandy.record.model.RecordQuery;
import com.sandy.record.model.RecordSave;
import com.sandy.record.model.RecordUpdate;


public interface RecordService {

    void doQuery(RecordQuery query);

    Collection<RecordInfo> loadRecordInfos();

    /**
     *   查询记录信息
     * 
     * @param recordCode
     * @return
     */
    RecordInfo queryRecordInfo(String recordCode);

    void doSave(RecordSave save);


    /**
     *  单记录查询
     * 
     * @param one
     */
    Map<String, Object> doOne(RecordOne one);

    void doUpdate(RecordUpdate update);
}
