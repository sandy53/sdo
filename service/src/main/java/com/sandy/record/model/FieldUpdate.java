package com.sandy.record.model;

import java.io.Serializable;

/**
 *  字段编辑模型
 * 
 * @author sandy
 * @version $Id: FieldUpdate.java, v 0.1 2019年5月9日 上午10:32:14 sandy Exp $
 */
public class FieldUpdate implements Serializable {

    /**  */
    private static final long serialVersionUID = -847395260539473938L;

    private String            fieldCode;
    private String            fieldColumn;
    private Object            value;


    public FieldUpdate() {
        super();
    }

    public FieldUpdate(String fieldCode, Object value) {
        super();
        this.fieldCode = fieldCode;
        this.value = value;
    }
    public String getFieldColumn() {
        return fieldColumn;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }
    public void setFieldColumn(String fieldColumn) {
        this.fieldColumn = fieldColumn;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

}
