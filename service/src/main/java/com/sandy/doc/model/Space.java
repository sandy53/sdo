package com.sandy.doc.model;

import java.io.Serializable;

import com.sandy.record.enums.RecordBase;
import com.sandy.record.util.Genernate;

/**
 *  空间
 * 
 * @author sandy
 * @version $Id: Space.java, v 0.1 2019年9月2日 上午9:56:25 sandy Exp $
 */
@Genernate(value = "sdo_space", desc = "文档空间目录")
public class Space extends RecordBase implements Serializable {

    /**  */
    private static final long serialVersionUID = -9051581997153980622L;

    @Genernate(value = "id", isId = true, desc = "文档空间目录表")
    private Integer           id;

    @Genernate(value = "code", desc = "文档空间编码")
    private Long              code;

    @Genernate(value = "ucode", desc = "用户编码")
    private Integer           ucode;

    @Genernate(value = "name", desc = "名称")
    private String            name;

    @Genernate(value = "create_time", desc = "创建时间")
    private Long              createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getCode() {
        return code;
    }

    public void setCode(Long code) {
        this.code = code;
    }

    public Integer getUcode() {
        return ucode;
    }

    public void setUcode(Integer ucode) {
        this.ucode = ucode;
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
