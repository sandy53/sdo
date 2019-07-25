package com.sandy.user.model;

import java.io.Serializable;

/**
 *  token模型
 * 
 * @author zhang
 * @version $Id: Token.java, v 0.1 2018年4月17日 上午9:58:43 zhang Exp $
 */
public class Token implements Serializable {

    /**  */
    private static final long serialVersionUID = 8618827098842105643L;

    private String            access_token;

    private String            refresh_token;

    private long              token_expires;

    private long              refresh_expires;

    /**
     * 用户等级
     * @return
     */
    private int                    grade;

    /**
     * 用户id
     * @return
     */
    private Integer                    userId;

    /**
     * 用户名称
     * @return
     */
    private String                    userName;

    /**
     * 用户手机号
     * @return
     */
    private String                    phoneNumber;

    /**
     * 用户邀请码
     * @return
     */
    private String                    invitationCode;

    /**
     * 邀请人的邀请码
     * @return
     */
    private String                    superInviterCode;

    /**
     * h5的openid--支付用
     * @return
     */
    private String                    openid;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getToken_expires() {
        return token_expires;
    }

    public void setToken_expires(long token_expires) {
        this.token_expires = token_expires;
    }

    public long getRefresh_expires() {
        return refresh_expires;
    }

    public void setRefresh_expires(long refresh_expires) {
        this.refresh_expires = refresh_expires;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getInvitationCode() {
        return invitationCode;
    }

    public void setInvitationCode(String invitationCode) {
        this.invitationCode = invitationCode;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSuperInviterCode() {
        return superInviterCode;
    }

    public void setSuperInviterCode(String superInviterCode) {
        this.superInviterCode = superInviterCode;
    }

    @Override
    public String toString() {
        return "Token{" +
                "access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", token_expires=" + token_expires +
                ", refresh_expires=" + refresh_expires +
                ", grade=" + grade +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", invitationCode='" + invitationCode + '\'' +
                ", superInviterCode='" + superInviterCode + '\'' +
                ", openid='" + openid + '\'' +
                '}';
    }
}
