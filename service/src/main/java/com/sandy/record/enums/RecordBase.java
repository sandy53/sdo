package com.sandy.record.enums;

import java.io.Serializable;



/**
 *    记录对象模型类 
 *           用于记录保存， 所有的记录对象必须要继承自己这个类才能保存
 * @author sandy
 * @version $Id: RecordBase.java, v 0.1 2019年4月13日 下午5:31:14 sandy Exp $
 */
public class RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -5391281149726581289L;

    protected RecordEnum      recordCode;



    public RecordEnum getRecordCode() {
        return recordCode;
    }

    public void setRecordCode(RecordEnum recordCode) {
        this.recordCode = recordCode;
    }

    @Override
    public String toString() {
        return "RecordBase [recordCode=" + recordCode + "]";
    }

}
