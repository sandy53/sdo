package com.sandy.doc.model;

import java.io.Serializable;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.util.Genernate;

@Genernate(value = "sdo_doc", desc = "文档主表")
public class Doc extends RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -7863623295505837341L;

    @Genernate(value = "id", isId = true, desc = "文档主表")
    private Integer id;
    @Genernate(value = "docId", desc = "文档编码")
    private String  docId;

    @Genernate(value = "space_code", desc = "文档空间编码")
    private String            spaceCode;

    @Genernate(value = "ucode", desc = "用户编码")
    private Integer           ucode;
    @Genernate(value = "title", desc = "标题")
    private String            title;

    @Genernate(value = "parent", desc = "上级文档编码")
    private String            parent;

    @Genernate(value = "leaf", desc = "是否为叶子节点")
    private Byte              leaf;

    private Long    createTime;

    //临时封装用
    private String            content;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public Byte getLeaf() {
        return leaf;
    }

    public void setLeaf(Byte leaf) {
        this.leaf = leaf;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSpaceCode() {
        return spaceCode;
    }

    public void setSpaceCode(String spaceCode) {
        this.spaceCode = spaceCode;
    }

}
