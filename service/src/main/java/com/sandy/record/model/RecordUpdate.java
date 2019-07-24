package com.sandy.record.model;

import java.util.ArrayList;
import java.util.List;

import com.sandy.record.enums.RecordEnum;


/**
 *  记录变更用模型
 * 
 * @author sandy
 * @version $Id: RecordSave.java, v 0.1 2019年4月13日 下午5:19:59 sandy Exp $
 */
public class RecordUpdate extends RecordQS {

    /**  */
    private static final long serialVersionUID = 7670499768194587783L;


    private Long               recordId;

    private Long              updateTime;

    /**
     * 需要修改的字段集
     */
    private List<FieldUpdate> updateFields;

    /**
     * 搜索条件字段集
     */
    private List<FieldUpdate> conditions;

    public RecordUpdate(RecordEnum recordEnum) {
        super();
        this.recordCode = recordEnum.name();
    }


    public void addCondition(String fieldCode, String value) {
        if (conditions == null) {
            conditions = new ArrayList<>();
        }
        conditions.add(new FieldUpdate(fieldCode, value));
    }

    public void addUpdateField(String fieldCode, Object value) {
        if (updateFields == null) {
            updateFields = new ArrayList<>();
        }
        updateFields.add(new FieldUpdate(fieldCode, value));
    }


    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public List<FieldUpdate> getUpdateFields() {
        return updateFields;
    }

    public void setUpdateFields(List<FieldUpdate> updateFields) {
        this.updateFields = updateFields;
    }

    public List<FieldUpdate> getConditions() {
        return conditions;
    }

    public void setConditions(List<FieldUpdate> conditions) {
        this.conditions = conditions;
    }

}
