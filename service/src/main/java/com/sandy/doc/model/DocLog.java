package com.sandy.doc.model;

import java.io.Serializable;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.util.Genernate;

/**
 * 
 * 
 * @author sandy
 * @version $Id: DocLog.java, v 0.1 2019年7月25日 下午6:01:14 sandy Exp $
 */
@Genernate(value = "sdo_doc_log", desc = "文档日志表")
public class DocLog extends RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -5570705265604669386L;
    @Genernate(value = "id", isId = true, desc = "文档主表")
    private Integer id;
    @Genernate(value = "docId", desc = "文档编码")
    private String  docId;
    @Genernate(value = "ucode", desc = "用户编码")
    private Integer ucode;
    @Genernate(value = "remarks", desc = "备注")
    private String  remarks;
    private Long    createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public Integer getUcode() {
        return ucode;
    }

    public void setUcode(Integer ucode) {
        this.ucode = ucode;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}
