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

    CODE_MISMATCH(2011, "验证码错误,请重新验证"),

    SENT_TO_UPPER_LIMIT(2012, "短信发送记录数达到上限"),

    SECOND_CAPTCHA_UPPER_LIMIT(2013, "需启动二次验证码"),

    SMS_FREQUENT(2014, "验证码时间间隔小于60s，请稍后再试"),

    NOT_LOGGED(401, "未登录,请登录后操作"),

    LOGIN_TIMEOUT(2016, "登录超时,请重新登录"),

    NO_PERMISSION(2017, "无权限"),

    USER_NOT_EXIST(2018, "会员不存在"),

    CART_UPPER_LIMIT(2019, "购物车商品数量达到上限，请先删除购物车部分商品后再添加"),

    RECORD_RELATION_NOT_MATCH(2020, "记录关联关系不匹配"),

    IMG_PARSE_ERROR(2021, "图片记录ID转换成URL失败"),

    SKU_STOCK_ZERO(2022, "已售罄"),

    SKU_STOCK_LOCK_ERROR(2023, "sku库存锁定异常"),

    USER_THIRD_UNBIND(2025, "第三方授权会员未绑定平台"),

    USER_THIRD_BINDED(2026, "第三方授权会员已绑定平台"), USER_THIRD_OPENID_FETCH_ERROR(2027, "无法获取第三方授权标识"),
    /**
     * 请求入参检查，日期格式不对
     */
    DATE_FORMAT_ERROR(2028, "日期格式不对"),
    GROUP_DEL_FAIL(3001, "属性组删除失败"),

    TEMPLATE_DEL_FAIL(3000, "模板删除失败"),

    ATTR_DEL_FAIL(3002, "属性项删除失败"),

    SPEC_DEL_FAIL(3003, "该规格值已绑定sku删除失败"),

    SKU_NOT_EXIST(4002, "不存在SKU"),

    SKU_PRICE_ZERO(4003, "在售商品价格不允许为0"),

    GOODS_ON_OFFER(4001, "商品在售"),

    GOODS_IMAGE_REQUIRED(400002, "商品图片必须"),

    GOODS_IMAGES_PARSE_FAILE(400004, "商品详情图片解析失败"),

    GOODS_SHELVE_FAILE_OF_NOCATEGORY(400005, "存在未绑定展示分类的商品"),

    SUPPLIER_NOT_EXISTS(4000006, "供应商不存在"),

    ADMIN_LOGIN_ERROR(4005, "登录失败"),

    USERNAME_PASSWORD_ERROR(4006, "用户名或密码错误"),

    USERNAME_DISABLED(4007, "用户已锁定"),

    USERNAME_LEAVE(4008, "用户名已离职"),

    COMPANY_ADDRESS_NOT_EXIST(4009, "公司缺少收货地址"),

    CART_OUT(4010, "该商品不允许加入购物车，请直接购买"),

    USER_TYPE_NOT_MATCH(4011, "操作用户不匹配"),

    USER_STSTUS_FAIL(4012, "申核状态错误"),

    USER_EMAIL_FAIL(4013, "邮箱格式不正确"),

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

    WECHAT_USERINFO_FAIL(5306, "获取微信用户信息失败"),

    WECHAT_USERINFO_ERROR(5307, "获取微信用户信息错误"),

    // ----------------------------------订单 模块 6XXX
    // --------------------------------------------------end
    // OrderInvoice订单发票 begin ===>
    OI_ORDERSN_ERROR(6001, "请输入订单编号"),

    OI_ORDER_ERROR(6002, "订单不存在"),

    OI_INVOICETYPE_ERROR(6003, "请输入发票类型"),

    OI_INVOICECONTENTTYPE_ERROR(6004, "请输入发票内容类型"),

    OI_INVOICETITLE_ERROR(6005, "请输入发票抬头"),

    OI_USEREMAIL_NULL_ERROR(6006, "请输入邮箱"),

    OI_PHONE_ERROR(6007, "请输入正确的手机号码"),

    OI_COMPANYNAME_ERROR(6008, "请输入公司名称"),

    OI_TAXPAYERREGISTERNUM_ERROR(6009, "请输入单位税号"),

    OI_ORDER_USER_ERROR(6010, "订单用户不一致"),

    OI_ERROR(6012, "订单发票不存在"),

    OI_USEREMAIL_ERROR(6013, "请输入正确的邮箱"),

    INVOICE_STATUS_NULL_ERROR(6014, "发票状态不能为空"),

    DATE_FORMAT_NOT_RIGHT(6015, "查询时间格式不正确"),

    FILE_NULL_ERROR(6016, "请选择文件上传"),

    INVOICE_ID_NULL_ERROR(6017, "发票id不能为空"),

    FILE_TYPE_NOT_PDF(6018, "请选择pdf格式文件"),

    OI_DETAIL_FAIL(6019, "获取订单发票详情失败"),

    OI_URL_ERROR(6020, "订单发票还未上传，请耐心等待"),

    OI_URL_FAIL(6021, "订单发票获取失败"),

    OI_SAVE_FAIL(6022, "订单发票保存失败"),

    OI_PHONE_NULL(6023, "请输入手机号码"),
    // 订单发票 end <===

    PAYMETHOD_ID_NULL(6031, "支付方式id为空"),

    PAYMETHOD_ISOPEN_NULL(60322, "是否启用为空"),

    SHARE_CODE_CREAT_FAIL(5305, "商品邀请码生成失败"),

    DELIVERY_LOCK(6033, "订单已锁定"),

    ORDER_NOT_FIND(6034, "未找到订单信息"),

    ORDER_STATUS_ERROR(6035, "有订单行不处于待发货状态，是否还继续更改地址"),

    PHONE_NULL(6036, "收货人手机号码为空"),

    CONSIGNEE_NULL(6037, "收货人姓名为空"),

    ADDRESS_NULL(6037, "收货地址为空"),

    // ----------------------------------订单 模块
    // --------------------------------------------------end

    // -----------------------------------优惠券----------------------------------------
    COUPONS_NOT_EXIST(7000, "优惠券已发放结束"), COUPONS_TO_EARLY(7001, "您来早了，活动还未开始哦"),
    COUPONS_HAVE_FINISHED(7002, "您来晚了，优惠券已被领完~~~请及时关注下一个场次"),

    // ----------------------------------订单 模块
    // --------------------------------------------------end

    // -----------------------------------pop店----------------------------------------
    POP_IS_NOT_STARTUP(6000, "pop店未启用"), RECEIVE_TYPE_ERROR(7001, "优惠券领取方式错误"),

    RECEIVE_TIME_ERROR(7002, "该兑换码已失效"),

    RECEIVE_NUM_BEYOND(7003, "您已达到领取上限，把机会留给其他人吧"),

    ACTIVATE_NOT_EXIST(7004, "该兑换码无效，请核对"),

    ACTIVATED(7005, "该兑换码已被兑换"),

    OTHER_ERROR(7006, "系统繁忙，请稍后再试!"),

    FIXED_SHIPPING_REPEAT_EXIST(7007, "同一时间存在相同spu的营销活动"),

    FREE_FEE_REPEAT_EXIST(7008, "免税免费活动时间配置重叠"),

    GOODS_NOT_EXIST(7009, "商品不存在,请核实"),

    GOODS_OFF_SALE(7010, "商品已下架"),

    COUPONS_SIGN_NOT_LEGAL(7011, "无效签名"),

    TIME_ERROR(7012, "开始时间大于结束时间"),

    COUPONS_NOT_RESIDUE(7013, "优惠券已领完"),

    SPECIAL_RECEIVE_BEYOND(7014, "您已使用该类券，无法再次领取"),

    ACTIVITY_RECORD_TIME_ERROR(7015, "时间参数错误"),

    COUPONS_CONVERT_NUMBER_LESS(7016, "转换码数量大于剩余优惠券张数请核对"),

    COUPONS_GRANT_NUMBER_LESS(7017, "发行量不足"),
    COUPONS_ACTIVE_END(7018, "活动已结束"),
    COUPONS_NOT_START(7019, "活动未开始，请稍后再来"),

    // -----------------------------------------------------------------------------------------
    NULL_PARAMETER(-3, "空参"),

    RECORD_HAVED(-2, "记录已经存在"),

    // ---------------------广告和广告位----------------------------------------------------------------

    AD_TOMUCH(8008, "广告条数不能超过20"),

    // -------------------------------最近上新和商品推荐--------------------------

    NEWSKU_IS_OVER_SIX(9000, "最近上新SKU必须是3个"),

    START_TIME_IS_OVER_ENDTIME(9001, "开始时间不得早于已有的有效时间"),

    EXISTS_ALIKE_NEWSKU(9002, "存在相同的最近上新"),

    EXISTS_ALIKE_RECOMMENDSKU(9003, "存在相同的商品推荐"),

    RECOMMEND_BLANK_PRODUCT_TO_MUSH(9050, "推荐商品不能超过30个"),

    NUM_IS_OVER_NINETY_NINE(9004, "排序数字最大为99"),

    NOT_OVER_FIRST(9005, "返利申请一天只操作一次"),

    CASH_AMOUNT_IS_LOW(9006, "提现金额不可超过可提现金额"),

    NO_CASH_AMOUNT(9007, "该会员没有可用提现金"), APPLY_CASH_TIME(9008, "请在7:00~24:00再进行提现申请哦~"),
    // ---------------------------关键字--------------------------
    KEYWORD_EXISTS(11001, "存在相同关键字"),

    KEYWORD_TO_MUCH(11002, "关键字个数最多10个"),

    // ---------------------------邮费
    MODULE_STORAGE_RRLATIONED_TAX(140001, "仓库已关联该种税费方式"),

    MODULE_NAME_ALREADY_EXISTENCE(140003, "模板名已存在"),

    MODULE_CITY_REPEAT(140004, "运费配置添加市重复"),

    MODULE_DEF_CONFIG_ISBLANK(140005, "默认运费配置为空"),

    // ---------------------运单说明-------------------------

    SHIPPINGNUMBER_IS_ILLEGAL(15001, "错误！运单号错误，系统中不存在！", "Error!  Wrong waybill no., does not exist in the system!"),

    SHIPPINGNUMBER_PACKED(15002, "错误！运单号已被打托在托盘，不能重复打托！",
            "Error! This waybill no. has been used in the pallet, cannot duplica"),

    SCAN_SUCCESS(15003, "扫描成功", "Scanning successfully"),

    NUMBER_OF_PALLET_NO(15004, "错误！该运单号还未打托！", "Error! This waybill no. is unpalletized！"),

    // ---------------------发货单说明---------------------------

    ORDER_DELIVERY_NOT_EXIST(16001, "错误！订单号错误，系统中不存在！", "Error!  Wrong order no. , does not exist in the system!"),

    ORDER_DELIVERY_NOT_IN_SERVICE(160001, "商品已发货或未在售后中"),

    ORDER_DELIVERY_IN_SERVICE(160002, "商品已在售后中"),

    ORDER_DELIVERY_ALREADY_PACKAGE_SUCCESS(16002, "错误！订单已打包，不能重复打包！",
            "Error! Order no.  has been packed，cannot be duplicate packaged!"),

    ORDER_DELIVERY_IS_UN_PRINT(16003, "错误！订单还未打印，请先打印订单", "Error ! Order no.   is unprinted, please print it first!"),

    ORDER_DELIVERY_EXCEPTION(16004, "错误！订单异常，请勿进行打包操作！", "Error ! Abnormal order, do not pack operation!"),

    ORDER_DELIVERY_GOOD_UN_MATCH(16005, "错误！扫描商品与订单内商品不匹配！",
            "Error! Scan products do not match the goods in the order!"),

    ORDER_DELIVERY_GOOD_ALREADY_PACKAGE(16006, "错误！对应的商品已打包，不能重复打包！",
            "Error! This product has been packed，cannot be duplicate packaged!"),

    ORDER_DELIVERY_STATUS_UN_MATCHING(16007, "错误！发货单状态与该操作不匹配",
            "Error! The invoice status do not match this operation! "),

    ORDER_DELIVERY_ALREADY_PACKAGE_FAIL(16008, "请注意，该订单已处于打包失败状态",
            "Be noted that this order is already in a package failure state！ "),

    ONE_CAN_TEN_PACKAGE(16009, "错误！一次只能添加10个包裹哦", "Error! Only 10 packages can be added at one time！ "),

    ORDER_STORAGE_NOT_ACCORDANCE(16010, "所增包裹与托盘不在同一仓库", "The added package and pallet are not in the same storage!"),

    // ======================文章==============
    ARTICLECOUMNS_HAVE_ARTICLE(17001, "栏目下有文章,不可进行删除"),

    // ---------------------------活动-----------------------------------------------

    ACT_STATUS_NOT_MATCH(17002, "活动状态不匹配"),

    ACT_JOIN_FAIL(17003, "参与失败"),

    ACT_AWARD_TIME_OUT(17004, "您已超时!"),

    ACT_AWARD_HASED(17005, "您已领奖！"),

    ACT_END_PHASE_CANNOT_SUSPENSION(17006, "该期已经是该活动的最后一期，不能进行中止操作！"),

    PHONE_NOT_MATCH_CITY(17007, "用户手机无法匹配归属地"),

    ACTIVITY_TYPE_UNSUPPORT(170001, "活动尚不支持"),

    ACTIVITY_CLOSED(170002, "活动已结束"),

    ACTIVITY_UNSTART(170003, "活动未开始"),

    ACTIVITY_SETTING_ERROR(170004, "活动配置读取错误"),

    ACTIVITY_UNKNOW_CYCLE_TYPE(170005, "未知时间周期类型"),

    ACTIVITY_SKU_ERROR(170006, "活动商品数据获取失败"),

    ACTIVITY_SKU_PARSE_ERROR(170007, "活动商品参数解析失败"),

    ACTIVITY_SKU_EXISTS(170008, "该商品已存在活动中"),

    ACTIVITY_UNOVER_REMOVE_FAIL(170009, "只能删除已结束或者已失效的活动"),

    ACTIVITY_INVALID(170010, "活动已失效"),

    ACTIVITY_LIMIT_NUMBER_OVERFLOW(170011, "超出活动商品购买数量"),

    // 活动 -------------------

    ACT_SEIZE_CLOSE(17008, "活动已结束!"),

    ACT_SEIZE_HELP_OVER(17009, "您已帮TA助力过了"),

    ACT_SEIZE_HELP_FULL(17010, "您的好友助力已满"),

    ACT_SEIZE_CANNOT_HELP_SELF(17011, "自己不能给自己助力哦"),

    ACT_SHARE_SIGN_NOT_VALID(17012, "签名不合法"),

    ACT_WINNING_NOT_MATCH(17013, "领奖人与中奖人信息不匹配"),

    ACT_TERMINATED(17014, "该期数已经被中止"),

    ACT_SEIZE_HELP_FAIL(17015, "助力失败"),

    ACT_CONFIG_CLOSE(17016, "夺宝活动开关已关闭"),

    IS_NO_SUSPENDED_ACTIVITY(17017, "没有可中止的活动"),

    INVITENUMBER_NOT_BE_GREATER_THANJOINNUMBER(17018, "邀请上限不得大于参与码个数"),

    PLEASE_USE_NEW_COUPON(17019, "请使用新的优惠券"),

    ACT_SEIZE_PHASE_CLOSE(17020, "本期活动已结束"),

    ACT_SEIZE_WINNER_CANNOT_REFUND(17021, "中奖后不能退款"),

    ACT_JOIN_CODE_DECI(17022, "参与码数量不足"),

    // ---------------------------商户-----------------------------------------------
    MERCHANT_USERNAME_ERROR(18001, "商户账号格式错误"),

    MERCHANT_USERNAME_ALREADY_EXISTS(18002, "商户账号已存在"),

    SHIFT_IN_SUCCESS(18003, "已成功移入，不能重复添加"),

    SHIFT_IN_FAIL(18004, "移入失败，请重新操作"),

    NO_MATCHING_DELIVERY_ADDRESS(18005, "暂无匹配的收货地址"),

    PASSWORD_ERROR_PLEASE_RE_ENTER(18006, "您的原密码错误，请重新输入"),

    PASSWORD_INCONSISTENCY(18007, "您输入的密码不一致，请再次输入"),

    MERCHANT_SKU_STATUS_CHANGE(18008, "商品状态已改变，无法下单"),

    SHIPPING_NAME_NOT_MATCH(18009, "该物流公司暂不支持查看面单"),

    UPDATE_TIME_OVER(18010, "您已修改过寄件人姓名，如需更改，请联系客服"),

    J_CONTACT_ERROR(18011, "请输入2-20位英文寄件人姓名"),

    MERCHANT_ADD_ERROR(18012, "添加商户失败"),

    MERCHANT_STORAGE_RELEVANCE_ERROR(18012, "商户仓库关联失败"),

    SUPPLIER_LIST_NULL(18013, "未找到供应商"),

    SUPPLIER_RELATED_GOODS(18014, "该商户存在关联的商品，无法删除"),

    GIFTORDER_REBUY(18016, "您已购买过礼包订单"),

    // -----------------------------------地址--------------------------------

    NOT_SUBMIT_DUPLICATE_INFORMATION(19001, "请勿提交重复通关证件信息"),

    MAX_UPDATE_THREE_TIMES(19002, "最多手动修改三次"),

    PELEASE_ENTER_THE_CORRECT_IDNUM(19003, "请输入正确的身份证号码"),

    NAME_AND_IDENTITY_CARD_NOT_AGREE(19004, "姓名与身份证不一致，可能会导致您的商品无法正常清关，请重新认证"),

    VERIFY_SUCCESS_CANNOT_UPDATE(19005, "已认证成功地址不能修改申购人证件信息"),

    // ----------------------------------新物流订单-----------------------------------
    MERCHANT_LOGSITIC_NOT_MATCH(20001, "此订单不是新物流订单"),
                                            /*	ACT_SEIZE_CLOSE(17008, "活动已结束!"),
                                            
                                            	ACT_SEIZE_HELP_OVER(17009, "您已帮TA助力过了"),
                                            
                                            	ACT_SEIZE_HELP_FULL(17010, "您的好友助力已满"),
                                            
                                            	ACT_SEIZE_CANNOT_HELP_SELF(17011, "自己不能给自己助力哦"),
                                            
                                            	ACT_SHARE_SIGN_NOT_VALID(17012, "签名不合法"),
                                            
                                            	ACT_WINNING_NOT_MATCH(17013, "领奖人与中奖人信息不匹配"),
                                            
                                            	ACT_TERMINATED(17014, "该期数已经被中止"),
                                            
                                            	ACT_SEIZE_HELP_FAIL(17015, "助力失败"),
                                            
                                            	ACT_CONFIG_CLOSE(17016, "夺宝活动开关已关闭"),
                                            
                                            	IS_NO_SUSPENDED_ACTIVITY(17017, "没有可中止的活动"),
                                            
                                            	INVITENUMBER_NOT_BE_GREATER_THANJOINNUMBER(17018, "邀请上限不得大于参与码个数"),
                                            
                                            	PLEASE_USE_NEW_COUPON(17019, "请使用新的优惠券"),
                                            
                                            	ACT_SEIZE_PHASE_CLOSE(17020, "本期活动已结束"),
                                            
                                            	ACT_SEIZE_WINNER_CANNOT_REFUND(17021, "中奖后不能退款"),
                                            
                                            	ACT_JOIN_CODE_DECI(17022, "参与码数量不足"),
                                            */
	// ---------------------------商户-----------------------------------------------

    MERCHANT_LOGSITIC_IS_SPLIT(20002, "发货失败，请取消订单，查看订单是否奶粉与其他商品一起购买，若无，还不能发货，请联系客服"),

    MERCHANT_GET_SHIPPING_NUM_ING(20003, "获取运单号中，请稍后再试"),

    // 用户账号
    ACCOUNT_NOT_EXISTS(22001, "账户未初始化"),

    ACCOUNT_LOCKED(22002, "账户已被锁定"),

    // -----------------------------------商盟--------------------------------
    SUM_ORDERSN_NOT_MATCH(21001, "收款订单不匹配"),

    SUM_PAYAMOUNT_NOT_MATCH(21002, "收款金额不匹配"),

    SUM_HANDLER_REPEAT(21003, "接口已处理成功，重复操作"),

    SUM_APPLY_NOT_MATCH(21004, "收款状态不匹配"),

    TOTAL_TOPIC_TEMP(22005, "专题模板数不能大于50个"),

    AFTER_SALE_REPEAT(21005, "此订单已提交主动售后，不能重复操作"),
    // -------------------------------------后台首页配置-----------------------------------------

    // ----------------------------------账单配置--------------------------------
    Bill_LIST_NULL(29001, "当前没有任何账单"),

    JSON_TRANSITION_FAIL(23002, "json转换失败"),
    // -------------------------------------提现 25XXX
    // -----------------------------------------

    // -------------------------------------提现 25XXX
    // -----------------------------------------

    BANK_CARD_PARAM_PHONE_ERROR(20061, "请填写正确的手机号码"),

    BANK_CARD_PARAM_VERIFYCODE_ERROR(20062, "请输入验证码"),

    LLPPAY_PARAM_FLAGCHNL_ERROR(20063, "请输入应用渠道标识"),

    LLPPAY_PARAM_CARDID_NULL(20064, "请输入支付卡id"),

    LLPPAY_PARAM_USER_ERROR(20065, "获取用户信息失败，请重新登录后再支付"),

    LLPPAY_PARAM_ORDERSN_ERROR(20066, "请输入订单编号"),

    LLPPAY_PARAM_CARDID_ERROR(20067, "请输入正确的支付卡id"),

    LLPPAY_CREATEBILL_ERROR(20068, "连连支付创建订单失败"),

    BANK_CARD_RESULT_ERROR(20069, "不支持的银行卡"),

    BANK_CARD_DECRYPTOR_ERROR(20070, "银行卡解密失败"),

    BANK_CARD_BIN_REQUEST_ERROR(20071, "银行卡bin请求失败"),

    JSONPARSE_EXCEPTION(20072, "json转换失败"),

    BANK_CARD_SAVE_ERROR(20073, "银行卡保存失败"),

    // -------------------------------------消息推送 26XXX
    // -----------------------------------------

    SMS_SEND_CLASS_PROVIDER_ERROR(26029, "短信提供商不存在"),

    SMS_SEND_FAIL(26030, "发送短信异常"),

    SMS_SEND_URL_ERROR(26031, "发送短信链接为空"),

    SMS_URL_ENCODE_ERROR(26032, "短信发送url encode失败"),

    SMS_TEMPLATE_KEY_NULL(26033, "短信模版key不能为空"),

    SMS_SMS_SUPPLIER_NULL(26034, "请选择短信服务商"),

    SMS_SMS_SEND_FAIL(26035, "短信发送失败"),

    SMS_SMS_SUPPLIER_ERROR(26036, "短信配置表记录不存在"),

    // -----------------------------------保障服务--------------------------------
    SET_IMG_SELECTION(2304, "请先配置标题图片"),
    // ---------------------------佣金-----------------------------------------------

    MGS_PAYLOAD_IS_NULL(31009, "消息体为空"),

    ORDER_SN_IS_NULL(31010, "订单号为空"),

    NO_PAY_ORDER(31011, "非支付订单"),

    COMMISSION_HAS_BEEN_DEALT(31012, "佣金已经被处理"),

    OBTAIN_ORDER_FAIL(31013, "获取订单信息失败"),

    OBTAIN_ORDER_REQUISITE_NOT_EXIST(31014, "获取订单信息必要信息不存在"),

    OBTAIN_ORDER_GOODS_NOT_EXIST(31015, "订单商品信息对象不存在"),

    COLLECTION_IS_NULL(31016, "集合对象为空"),

    PARENT_NOT_EXIST(31017, "父级店主不存在"),

    ALREADY_IS_SHOPKEEPER(31018, "已经是店主"),

    INVITATION_CODE_IS_NULL(31019, "邀请码不存在"),

    PETTY_CASH_EXPEND_FAIL(31020, "备用金支出失败"),

    PETTY_CASH_INSUFFIVIENT_BALANCE(31021, "备用金余额不足"),

    COMMISSION_HANDLER_NOT_EXIST(31022, "佣金处理HANDLER不存在"),

    COMMISSION_INFO_IS_NULL(31023, "佣金对象为空"),

    COMMISSION_ORDER_STATUS_NO_SUC(31024, "佣金订单状态为非成功状态"),
    // ----------------------------佣金----------------------------------------------
    // -------------------------------------提现 25XXX
    // -----------------------------------------

    DRAWCASH_BANKCARD_EMPUT(25001, "银行卡为空"),

    DRAWCASH_ORDER_NOT_EXIST(25002, "提现订单不存在"),

    DRAWCASH_STATUS_WRONG(25003, "提现状态不正确"),

    DRAWCASH_LLP_ENCRYPT_ERROR(25004, "连连支付加密异常"),

    DRAWCASH_LLP_PAYMENT_FAIL(25005, "连连支付申请付款失败"),

    DRAWCASH_LLP_CHECKSIGN_ERROR(25006, "返回结果验签异常,可能数据被篡改"),

    DRAWCASH_LLP_QUERYPAYMENT_ERROR(25007, "实时付款查询接口响应异常"),

    DRAWCASH_SAVE_DRAWCASH_ERROR(25008, "更新提现记录异常"),

    DRAWCASH_CASHAMOUNT_ERROR(25009, "提现金额必须大于0"),

    DRAWCASH_CASHAMOUNT_LESSTHAN_CONFIG(25010, "提现金额必须大于最低提现金额"),

    DRAWCASH_FAIL(25011, "提现失败"),

    DRAWCASH_CASHBANKCARD_ERROR(25012, "请选择银行卡"),

    DRAWCASH_DATA_ERROR(25013, "用户账户冻结余额小于提现余额，请联系技术人员排查问题"),

    BANK_CARD_NOT_EXIST(25014, "当前银行卡暂不支持哦"),

    BANK_CARD_BINDING_EXIST(25015, "该银行卡已经绑定过"),

    DRAWCASH_ORDERSN_NULL(25016, "提现订单编号为空"),

    DRAWCASH_ORDERID_NULL(25017, "提现订单ID为空"),

    DRAWCASH_LOCK(25018, "正在执行提现付款操作，请勿重复提交"),

    DRAWCASH_LLP_ERROR(25019, "连连支付查询接口异常，请到商户站查询订单情况"),

    DRAWCASH_LLP_BALANCEMONEY(25020, "连连支付可提现余额不足"),

    BANK_CARD_CHECK_EXCEPTION(25021, "调用三方验证接口异常"),

    BANK_CARD_CHECK_SUCCESS(25022, "三方验证通过"),

    BANK_CARD_CHECK_FAILURE(25023, "请输入正确的银行卡身份信息"),

    DRAWCASH_REFUSE_REASON_NULL(25024, "提现订单ID为空"),

    BANK_CARD_CHECK_PARAM_ERROR(20054, "请输入正确的银行卡身份信息"),

    BANK_CARD_UNABLED(20055, "当前银行卡暂不支持哦"),

    BANK_CARD_TYPE_UNABLE(20056, "当前暂不支持信用卡哦"),

    BANK_CARD_PARAM_NAME_ERROR(20057, "请输入正确的姓名"),

    BANK_CARD_PARAM_NO_ERROR(20058, "请输入正确的银行卡号"),

    BANK_CARD_PARAM_ID_ERROR(20059, "请输入正确的身份证号码"),

    BANK_NAME_NOT_RIGHT(20060, "请选择正确的银行名称"),
    // -------------------------------------消息推送 26XXX
    // -----------------------------------------
    APP_PUSH_OS_TYPE(26001, "请输入来源类型"),

    APP_PUSH_OS_TYPE_UNDEFIND(26002, "未定义的来源类型"),

    APP_PUSH_TOKEN(26003, "devicetoken不能为空"),

    APP_PUSH_MESSAGE_NULL(26004, "请填写消息推送内容"),

    APP_PUSH_CUSCOPE_NULL(26005, "请选择发送对象"),

    APP_PUSH_CUSCOPE_ERROR(26006, "请选择正确的发送对象"),

    APP_PUSH_CUSIDS(26007, "请填写指定发送人"),

    APP_PUSH_PUSHTYPE(26008, "请选择消息类型"),

    APP_PUSH_DESC(26009, "消息描述不能为空"),

    APP_PUSH_TITLE(26010, "消息标题不能为空"),

    APP_PUSH_TEXT(26011, "消息内容不能为空"),

    APP_PUSH_CUS_SAVE_FAIL(26012, "消息发送用户保存失败"),

    APP_PUSH_EXTRA_SAVE_FAIL(26013, "消息扩展表保存失败"),

    APP_PUSH_TASK_ID_FAIL(26014, "taskId不存在"),

    APP_PUSH_MESSAGE_STATUS(26015, "消息的状态不可以为空"),

    APP_PUSH_MESSAGEID(26016, "消息id为空"),

    APP_PUSH_HTTPREAD_FAIL(26017, "友盟消息读取失败"),

    APP_PUSH_CUSIDS_ERROR(26018, "请检查指定发送人"),

    APP_PUSH_SEND_TYPE(26019, "请选择发送类型"),

    APP_PUSH_SENDTIME(26020, "请选择发送时间"),

    APP_PUSH_KV(26021, "请输入正确的kv格式json串"),

    APP_PUSH_TIME(26022, "推送时间必须大于当前时间"),

    APP_CONFIRM_CODE_VALIDATE(26023, "付款确认码(confirm_code)为空"),

    APP_PUSH_SENDTIME_ERROR(26024, "请检查发送时间"),

    APP_PUSH_MESSAGE_ERROR(26025, "消息为空"),

    APP_PUSH_STATUS_ERROR(26026, "不是进行中的消息不能取消"),

    APP_PUSH_CANCEL_FAIL(26027, "消息任务取消失败"),

    APP_PUSH_LOCK_FAIL(26028, "获取lock失败"),

    SMS_SEND_LIMIT(26029, "发送太频繁"),

    // -----------------------------------保障服务--------------------------------

    GOODS_SERVICE_STOP_DEL(27001, "该服务为默认服务，不可删除"),

    // -----------------------------------频道配置--------------------------------
    CHANNEL_NOT_EXIT(28001, "频道不存在"),

    BACKGROUND_STATE_FAIL(23001, "后台首页配置项修改状态失败"),

    SLIDER_SAVE_FAIL(2302, "后台保存/更新轮播图失败"),

    INDEX_IS_NULL(2303, "ES中不存在该记录"),
    // --------------------------------------------------------------------------
    INVALID_INVITATION_CODE(31001, "邀请码无效"),

    BIND_INVITATION_FAIL(31002, "邀请码绑定失败"),

    REFUND_BALANCE_REQUEST_FAIL(31003, "退款请求订单失败"),

    IABNORMAL_INVITATION_CODE(31004, "邀请码异常"),

    INVITATION_CODE_NULL(31005, "网络开小差了，请重新提交订单"),

    USER_GRADE_INVALID(31006, "用户等级无效"),

    GIFT_AFTER_SALE_USER_STATE_ILLEGAL(31007, "礼包售后等级店主状态不允许"),

    MYSELF_INVITATION_CODE(31008, "邀请码为自己"),
    // -------------------------------------提现 25XXX
    // -----------------------------------------

    // -----------------------------------频道配置--------------------------------
    SHIPPING_ORDER_NOT_EXIST(29001, "没有此订单编号的物流信息"),


    //-----------------------------------弹框配置
    POPUP_NAME_REQUIRED(7020, "弹窗名称必填"),

    SELECT_START_TIME(7021, "请选择开始时间"),

    SELECT_END_TIME(7022, "请选择结束时间时间"),

    START_TIME_THAN_CURRENT_TIME(7023, "开始时间或者结束时间要大于当前时间"),

    NOT_OPTIMAL_CONDITION(7024, "此新增弹窗不满足最优条件,新增失败!"),

                                            USER_PHONE_NOT_NULL(7025,
                                                                "手机号码不能为空"), CHANNEL_CODE_NOT_NULL(7026,
                                                                                                   "渠道码不能为空"), NOT_GRANT_USER(7027,
                                                                                                                              "没有要发放的用户!"), GRANT_LOG_NULL(7028,
                                                                                                                                                           "发放的记录为空!"), EXCEED_GET_NUMBER(7029,
                                                                                                                                                                                          "领取优惠券数量已达到上限!"), COUPON_NUMBER_INSUFFICIENT(7030,
                                                                                                                                                                                                                                       "优惠券数量不足!"),

    //-----------------------------------供应商收发货仓
    SHOP_PLACE_NAME_NOT_EMPTY(8010, "请输入发货地名称"),

    SHOP_PLACE_CONTACTER_NOT_EMPTY(8011, "请输入退货联系人"),

    SHOP_PLACE_MOBILE_NOT_EMPTY(8012, "请输入退货联系电话"),

    SHOP_PLACE_CITY_NOT_EMPTY(8013, "请选择省市区"),

    SHOP_PLACE_ADDRESS_NOT_EMPTY(8014, "请输入详细地址"),

    SHOP_PLACE_ID_NOT_EMPTY(8015, "仓库id不能为空"),


    //-----------------------------------供应商收发货仓

    GOODS_EDIT_FAIL(10001, "服务请求失败，该商品已被编辑，请刷新后重试"),

    GOODS_EDIT_USER_NOT_MATCH(10002, "服务请求失败，该商品不是当前账户所属，请退出帐号后重试"),

    GOODS_DRAFT_NOT_EXIST(10003, "商品草稿不存在，请刷新后再试"),

    GOODS_DRAFT_ID_NULL(10004, "商品草稿ID不能为空"),

    GOODS_DRAFT_NULL(10005, "商品不能为空"),

    GOODS_UPDATE_FAIL(10005, "更新商品失败"),


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
