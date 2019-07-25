package com.sandy.common.model;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回状态码枚举
 * <p>
 * 公共用
 * <p>
 * 各模块返回码可实现 RespCode接口单独定义
 *
 * @author sandy
 * @version $Id: ResultCode.java, v 0.1 2019年4月18日 下午1:40:32 sandy Exp $
 */
public enum ResultCode implements RespCode {

    SUCCESS(1000, "成功"), FIELD(9000, "操作失败!"),

    REQUEST_SUCCESS(1001, "操作请求成功，操作结果请稍后刷新查询"),

    REQUEST_URL_ERROR(1002, "404"),

    HTTP_CONNECT_TIME_OUT(1003, "请求超时"),

    HTTP_STOCK_TIME_OUT(1004, "请求传输超时"),

    HTTP_REQUEST_ERROR(1005, "HTTP请求错误"),

    REQUEST_TOO_FREQUENT(1006, "请求过于频繁, 请缓缓"),

    SYS_PARAMETER_NOT_EXISTS(1010, "系统参数不存在"),

    REQUEST_DATA_IS_EMPTY(1011, "数据为空"),

    COMMISION_WHITE_LIST_UNSET(100100, "whites unset!"),

    COMMISION_WHITE_LIST_CHECK_FAIL(100101, "whites list check fail!"),

    API_NOT_IN_SERVICE(100105, "功能接口已暂停使用"),

    HOST_ORDER_IS_BLANK(1011, "订单模块地址为空"),

    IS_OVER_EIGHT(1013, "首页商品推荐不能超过8件"),

    METHOD_CALL_PARAMETER_ERROR(100012, "请求参数有误，请按照规则填写！"),

    BATCH_PROCESSING_NUMBER_OVER(100013, "批量处理条数已超过限制!"),

    FAIL(9999, "系统异常，请刷新后重试", "system error ! "),

    APP_FAIL(999999, "服务请求失败"),

    SERVICE_UNUSE(200000, "服务升级中，请稍候再试"),

    SERVICE_COLSE(200001, "服务升级中,暂时关闭，请稍候再试"),

    DATABASE_ERROR(200002, "数据异常"),

    FILE_UPLOAD_FAIL(200003, "文件上传失败"),

    FILE_UPLOAD_TOOLARGE(200004, "文件太大，上传失败"),

    FILE_UPLOAD_TRANSCODE(200005, "文件转码失败"),

    REDIS_LOCK_TIMEOUT(200010, "Redis分布式锁获取异常"),

    REDIS_LOCK_KEY_UNSUPPORT(200011, "Redis分布式锁KEY不支持"),

    REDIS_LOCK_TIMEOUT_OVERFLOW(200012, "Redis分布式锁超时时间设置越界"),

    USER_ALREADY_NO_EXISTS(20002, "该账号不存在"),

    REDIS_LOCK_TARGET_EXISTS_BUTNULL(200013, "Redis分布式锁目标应存在但为NULL"),

    RECORD_SAVE_FAIL(2000, "记录保存失败"),

    PARAMETER_ERROR(2001, "参数错误"),

    /**
     * 请求入参检查，必填参数不允许为空
     */
    PARAMETER_NULL(200002, "必填参数不允许为空"),

    RECORD_NOT_EXIST(2002, "记录不存在"),

    COLUMN_NOT_EXIST(2003, "栏目图片还没有上传"),

    USER_ALREADY_EXISTS(20002, "该账号已存在"),

    RECORD_ALREADY_EXISTS(2003, "记录已存在"),

    RECORD_STATUS_NOT_MATCH(2004, "记录状态不匹配"),

    RECORD_UPDATE_FAIL(2005, "记录更新失败"),

    IMAGE_OVER_LIMIT(2006, "图片张数超过限制"),
                                            EXCEL_FILE_PARSE_ERROR(100014, "excel文件解析失败"),


    OPTIMISTIC_EXCEPTION(2007, "数据修改锁异常"),

    DUPLICATE_DATA(2008, "重复数据"),

    HTTP_PARAM_BIND_FAIL(2009, "http请求参数绑定异常"),

    PASSWORD_ERROR(2010, "账号名密码错误"),

                                            VCODE_MISMATCH(2011, "验证码错误,请重新验证"),

    SENT_TO_UPPER_LIMIT(2012, "短信发送记录数达到上限"),

    SECOND_CAPTCHA_UPPER_LIMIT(2013, "需启动二次验证码"),

    SMS_FREQUENT(2014, "验证码时间间隔小于60s，请稍后再试"),

    NOT_LOGGED(401, "未登录,请登录后操作"),

    LOGIN_TIMEOUT(2016, "登录超时,请重新登录"),

    NO_PERMISSION(2017, "无权限"),

    USER_NOT_EXIST(2018, "会员不存在"),

    CART_UPPER_LIMIT(2019, "购物车商品数量达到上限，请先删除购物车部分商品后再添加"),

    RECORD_RELATION_NOT_MATCH(2020, "记录关联关系不匹配"),


    /**
     * 请求入参检查，日期格式不对
     */
    DATE_FORMAT_ERROR(2028, "日期格式不对"),
    GROUP_DEL_FAIL(3001, "属性组删除失败"),

    TEMPLATE_DEL_FAIL(3000, "模板删除失败"),

    ATTR_DEL_FAIL(3002, "属性项删除失败"),

    SPEC_DEL_FAIL(3003, "该规格值已绑定sku删除失败"),



    ADMIN_LOGIN_ERROR(4005, "登录失败"),

    USERNAME_PASSWORD_ERROR(4006, "用户名或密码错误"),

    USERNAME_DISABLED(4007, "用户已锁定"),

    USERNAME_LEAVE(4008, "用户名已离职"),

    COMPANY_ADDRESS_NOT_EXIST(4009, "公司缺少收货地址"),

    CART_OUT(4010, "该商品不允许加入购物车，请直接购买"),

    USER_TYPE_NOT_MATCH(4011, "操作用户不匹配"),

    USER_STSTUS_FAIL(4012, "申核状态错误"),

    USER_EMAIL_FAIL(4013, "邮箱格式不正确"),

                                            USER_EMAIL_SEND_FAIL(400013, "邮箱发送失败"),

    USER_EMAIL_REPEAT(4014, "邮箱已存在"),

    USER_ALIAS_REPEAT(4015, "昵称已存在"),

    USER_ALIAS_FAIL(4016, "昵称中含有特殊符号"),

    EMAIL_SEND_UP_LIMIT(4017, "邮件发送记录数达到上限"),

    USER_HAS_NOT_PROXY(4018, "该代理下没有此会员"),

    USER_CHECK_FAIL(4019, "申核操作错误"),

    USER_LOGIN_ERROR(4020, "登录密码出错超过系统限制次数，请通过验证码重置密码"),
	COUPON_LOOT_ALL(3004, "抱歉,优惠券被抢光了～"),


    USER_OPERATOR_STATUS_FAIL(4021, "用户操作状态不匹配"),

    USER_PAY_ERROR(4022, "请前往个人中心-商户信息进行重置支付密码再试！"),

    USER_NOT_SUFFICIENT_FUNDS(4023, "账户余额不足!"),

    USER_PASSWORD_IS_BLANK(4024, "您还未设置支付密码，请前往‘个人中心-商户信息’补充支付密码"),

    USER_PASSWORD_ERROR_SURPLUS_ONE(4025, "支付密码错误，您还有1次机会！"),

    USER_PASSWORD_ERROR_SURPLUS_TWO(4026, "支付密码错误，您还有2次机会！"),

    USER_PAY_PASSWORD_ERROR(4027, "支付密码错误！"),

    USER_VERIFYCODE_EXPIRE(4028, "验证码错误，请重新发送"),

    USER_PHONE_NULL_EXPIRE(4029, "手机号码不正确，请重新输入"),

    USER_PHONE_EXPIRE(4030, "该手机号已被注册"),

    USER_REGISTRY_EXPIRE(4031, "注册失败"),

    USERNAME_LOGIN_DISABLED(4031, "登录已锁定，请30分钟后再次尝试登录"),

    ROLE_SAME_EXPIRE(4032, "存在相同角色名"),

    MOBILE_MISMATCH_CUSTOMERID(4033, "绑定的手机号码与用户id不匹配"),

    CUSTOMTERID_HASBINDING_MULTIPLE(4034, "当前手机已绑定其他微信"),

    BINDING_TIMEOUT(4035, "等待时间超长，请重新绑定"),

    THIRD_HAS_BINDING(4036, "当前微信已被其他手机绑定"),

    WECHAT_ACCESSTOKEN_FAIL(4037, "获取微信sns的access_token失败，请退出重新进入"),

    USER_CUSTOMERUPDATE_FAILED(4038, "用户修改个人资料未更改"),

    USER_UPDATETYPE_FAILED(4039, "用户需要修改类型为空"),

    CODE_NULL_ERROR(4050, "编码为空"),

    NOTICE_TYPE_NULL(4051, "消息类型为空"),

    OPERATOR_TYPE_UNKNOW(5001, "未知的操作员类型"),

    SKU_STOCK_LESS(5024, "商品库存不足"),

    SKU_STOCK_UPDATE_LOCK_TIMEOUT(5025, "商品库存更新锁获取超时"),

    ORDER_TAX_AMOUNT_LESS(5072, "订单税费过低"),

    AFTER_SALE_NULL(5303, "主动售后相关数据为空"),

    HEADER_PARAM_ERROR(5304, "请检查header参数"),



    // ----------------------------------订单 模块 6XXX


    // ----------------------------------订单 模块
    // --------------------------------------------------end



    ;

    // 成员变量
    private int code; // 编码
    private String desc; // 中文描述
    private String enDesc; // 英文描述
    /**
     * 返回码MAP
     */
    private static Map<Integer, RespCode> map;

    static {
        // 初始化
        map = new HashMap<>();
        for (ResultCode code : ResultCode.values()) {
            map.put(code.getCode(), code);
        }


    }

    private ResultCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private ResultCode(int code, String desc, String enDesc) {
        this.code = code;
        this.desc = desc;
        this.enDesc = enDesc;
    }

    public static RespCode getType(int code) {
        if (code <= 0) {
            return null;
        }
        return map.get(code);
    }

    @Override
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * 获取返回码反描述
     *
     * @param
     * @return
     */
    public static String getDesc(int code) {
        RespCode respCode = map.get(code);
        return respCode == null ? "" : respCode.getDesc();
    }

    @Override
    public String getEnDesc() {
        return enDesc;
    }

    public void setEnDesc(String enDesc) {
        this.enDesc = enDesc;
    }

}
