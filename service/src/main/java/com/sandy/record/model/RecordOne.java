package com.sandy.record.model;

import java.util.ArrayList;
import java.util.List;

import com.sandy.record.enums.RecordEnum;



/**
 *  查询单条记录模型
 * 
 * @author sandy
 * @version $Id: RecordOne.java, v 0.1 2019年5月23日 下午4:51:36 sandy Exp $
 */
public class RecordOne extends RecordQS {

    /**  */
    private static final long serialVersionUID = 7670499768194587783L;

    /**
     * 搜索条件字段集
     */
    private List<FieldUpdate> conditions;

    public RecordOne(RecordEnum recordEnum) {
        this.recordCode = recordEnum.name();
    }

    public void addCondition(String fieldCode, String value) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(new FieldUpdate(fieldCode, value));
    }
    public List<FieldUpdate> getConditions() {
        return conditions;
    }

    public void setConditions(List<FieldUpdate> conditions) {
        this.conditions = conditions;
    }


}
