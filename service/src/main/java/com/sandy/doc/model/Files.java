package com.sandy.doc.model;

import java.io.Serializable;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.util.Genernate;

@Genernate(value = "sdo_files", desc = "文件上传记录表")
public class Files extends RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -7863623295505837341L;

    @Genernate(value = "code", desc = "文件编码名称")
    private String            code;
    @Genernate(value = "ucode", desc = "用户编码")
    private String  ucode;
    @Genernate(value = "path", desc = "存储路径")
    private String            path;

    @Genernate(value = "name", desc = "商品原名称")
    private String            name;


    private Long    createTime;

    public String getFileName() {
        return code + name.substring(name.lastIndexOf("."));
    }


    public String getCode() {
        return code;
    }


    public void setCode(String code) {
        this.code = code;
    }


    public String getUcode() {
        return ucode;
    }


    public void setUcode(String ucode) {
        this.ucode = ucode;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }


}
