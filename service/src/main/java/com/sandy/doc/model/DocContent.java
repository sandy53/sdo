package com.sandy.doc.model;

import java.io.Serializable;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.util.Genernate;

@Genernate(value = "sdo_doc_content", desc = "文档内容表")
public class DocContent extends RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -5095447623637850636L;

    @Genernate(value = "docId", desc = "文档编码")
    private String            docId;
    @Genernate(value = "content", desc = "文档内容")
    private String            content;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
