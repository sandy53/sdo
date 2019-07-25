package com.sandy.user.model;

import java.io.Serializable;

/**
 * 用户模型
 * 
 * @author sandy
 * @version $Id: Users.java, v 0.1 2019年7月24日 下午7:31:23 sandy Exp $
 */
public class Users implements Serializable {

    /**  */
    private static final long serialVersionUID = 1L;

    private Integer           ucode;

    private String            email;

    private String            nickName;

    private Byte              state;

    private Long              createTime;

    private Long              updateTime;

    public Integer getUcode() {
        return ucode;
    }

    public void setUcode(Integer ucode) {
        this.ucode = ucode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

}
