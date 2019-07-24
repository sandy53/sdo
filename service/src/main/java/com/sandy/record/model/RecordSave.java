package com.sandy.record.model;

import java.util.List;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.enums.RecordEnum;



/**
 *  记录保存用模型
 * 
 * @author sandy
 * @version $Id: RecordSave.java, v 0.1 2019年4月13日 下午5:19:59 sandy Exp $
 */
public class RecordSave extends RecordQS {

    /**  */
    private static final long serialVersionUID = 7670499768194587783L;


    /**
     * 需要的保存的记录
     */
    private List<RecordBase>   records;

    /** 需要保存的列*/
    protected List<RecordField> saveFields;

    /**
     * 由 records 转换而来能保存的值 
     */
    private List<List<String>> Values;


    public RecordSave(RecordEnum recordEnum) {
        super();
        this.recordCode = recordEnum.name();
    }
    public RecordSave(String recordCode, List<RecordBase> records) {
        super();
        this.recordCode = recordCode;
        this.records = records;
    }

    public List<List<String>> getValues() {
        return Values;
    }

    public void setValues(List<List<String>> values) {
        Values = values;
    }

    public List<RecordBase> getRecords() {
        return records;
    }

    public void setRecords(List<RecordBase> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "RecordSave [records=" + records + ", Values=" + Values + "]";
    }

    public List<RecordField> getSaveFields() {
        return saveFields;
    }

    public void setSaveFields(List<RecordField> saveFields) {
        this.saveFields = saveFields;
    }

}
