
package com.sandy.common.redis.key;

/**
 * 能用redisKey
 *
 * @author zhangyg
 * @version $Id: CommonKey.java, v 0.1 2019年1月31日 上午11:41:44 zhangyg Exp $
 */
public enum CommonKey implements RedisKey {
	AA_TEST_KEY,

	// 统计-----------------------------------------------------------start
	// ----------------------------------
	DICT_RECORD_INFO_KEY,
	/**
	 * 记录临时统计数据
	 **/
//	COUNT_TEMP("COUNT_TEMP:"),
	/**
	 * 记录临时统计数据 上架
	 **/
	COUNT_ON_SALE_TEMP("COUNT_ON_SALE_TEMP:"),
	/**
	 * 记录临时统计数据 下架
	 **/
	COUNT_OFF_SALE_TEMP("COUNT_OFF_SALE_TEMP:"),
	/**
	 * 记录临时统计数据 下单sku
	 **/
	COUNT_ORDER_SKU_TEMP("COUNT_ORDER_SKU_TEMP:"),

	COUNT_DATA("COUNT_DATA:"),

	COUNT_SYNC_TIME("COUNT_DATA:SYNC_TIME"),

	LOCK_DATA_SYNC_TIME("LOCK:DATA_SYNC_TIME"),
	
	// 统计-----------------------------------------------------------end
	// ----------------------------------

	// 活动 -----------------------------------------------------------start
	// ----------------------------------
	/**
	 * 活动特卖倒计时
	 */
	TIME_LIMIT_KEY("TIME_LIMIT_KEY"),
	/**
	 * 活动SKU用： sku -> SKU活动对象 里面的活动对应到 ACTIVITY_SELF
	 */
	ACTIVITY_SKU("ACTIVITY_SKU"),
	/**
	 * 活动用： spuId -> spu简单模型，主要为sku 数据
	 */
	ACTIVITY_SPU("ACTIVITY_SPU"),
	/**
	 * 活动用： 活动本身 -> 活动对象
	 */
	ACTIVITY_SELF("ACTIVITY_SELF"),
	/**
	 * 活动统计用
	 */
	ACTIVITY_COUNT_KEY("ACTIVITY_COUNT_"),

	/**
	 * 活动商品限购 数量统计 ACTIVITY_LIMIT_COUNT_{activityCode}_{skuId}, {userId} -> {count}
	 */
	ACTIVITY_LIMIT_COUNT_KEY("ACTIVITY_LIMIT_COUNT_"),

	/**
	 * 时间轴待配置集合
	 */
	ACTIVITY_TIMELINE_SETTING_KEY("ACTIVITY_TIMELINE_SETTING_KEY"),

	// 活动 -----------------------------------------------------------end
	// ----------------------------------
	// 商品 -----------------------------------------------------------start
	// ----------------------------------

	// 设置用
	/**
	 * 用于设置的sku信息缓存
	 */
	GOODS_SKU_FOR_SETTING_KEY("GOODS_SKU_FOR_SETTING_KEY"),

	// 商品 礼包
	GOODS_GIFT_LIST("PS_GOODS_GIFT_LIST"),

	// 商品收藏详情
                                           USER_GOODS_COLLECTION("USER_GOODS_COLLECTION:"),

	/**
	 * 商品可用优惠券
	 */
	GOODS_COUPON_KEY("PS_GOODS_COUPON_KEY"),

	/**
	 * 商品服务保障
	 */
	GOODS_SERVICE_KEY("PS_GOODS_SERVICE_KEY"),

	/**
	 * 商品供应商
	 */
	GOODS_SUPPLIER_KEY("PS_GOODS_SUPPLIER_KEY"),

	/**
	 * 商品标签
	 */
	GOODS_LABEL_KEY("PS_GOODS_LABEL_KEY"),

	// 商品 ----------------------------------------------------------- end
	// ----------------------------------
	// 认证
	/** 认证： token 对应的 用户编码 */
	AUTH_TOKEN_USER_PREFIX("AUTH:TOKEN_USER_"),

	/** 认证： 刷新token 对应的 用户编码 */
	AUTH_TOKEN_REFRESH_PREFIX("AUTH:TOKEN_R_USER_"),

	/** 认证： 用户信息 */
	AUTH_USER_KEY("AUTH:USER"),


	/** 第三方登录 绑定 */
	THIRD_LOGIN_BINDING,

	/**
	 * 日志汇总
	 */
	THING_LOG_COUNT_KEY("PS_THING_LOG_COUNT"),

	/**
	 * 认证： 用户基本信息（app用户登录分佣使用）
	 */
	USER_KEY("USER:INFO"),

	/**
	 * 用户基本信息，服务间各用 以用户名存储 后续调整为存储用户ID
	 */
	USER_BY_USERNAME("USER:USERNAME"),
	/**
	 * 用户基本信息，服务间各用 以ID存储 和 USER_KEY 重复
	 */



	/**
	 * 短信发送条数限制
	 */
	/** 用户验证码信息 */
	USER_CPATCHA_KEY("USER_CPATCHA_"),

	/** 短信发送条数限制 */
	SMS_SEND_LIMIT("SMS_SEND_LIMIT"),

	/**
	 * 消息通知用，购买礼包的用户订单信息
	 **/
	MESSAGE_CUSTOMERID_GIFTORDERINFO("MESSAGE:CUSTOMERID_GIFTORDERINFO:"),

	/**
	 * 消息通知用，购买礼包的用户订单信息
	 **/
	MESSAGE_CUSTOMERID("MESSAGE:CUSTOMERID:"),

	/**
	 * 消息推送小红点显示
	 **/
	NOTICEKEY_CUSTOMERID("NOTICEKEY_CUSTOMERID:"),

	/**
	 * 消息推送的用户信息
	 **/
	NOTICEKEY_SKUINFO("NOTICEKEY_SKUINFO:"),

	// ------------------------------app start
	// ---------------------------------------

	APP_CATEGORY_KEY("PS_APP_CATEGORY"),

	/**
	 * APP 商品详情
	 */
	APP_GOODS_DETAIL_KEY("PS_APP_GOODS_DETAIL"),

	APP_GOODS_BIG_DETAIL_KEY("PS_APP_GOODS_BIG_DETAIL"),

	// --------------------------------app end----------------------

	///
	// ------------------------------DICT START
	/// ---------------------------------------

	/**
	 * 字典： 地区前缀
	 */
	DICT_REGION_PROVINCE("DICT:REGION_PROVINCE"),

	DICT_REGION_CITY("DICT:REGION_CITY"),

	DICT_REGION_DISTRICT("DICT:REGION_DISTRICT"),

	/** 字典： 标签列表 */
	DICT_LABEL_KEY("DICT:GOODS_LABEL"),

	/** 字典： 分类列表 */
	DICT_CATEGORY_KEY("DICT:CATEGORY"),

	/** 字典： 分类列表 */
	DICT_VERSION_KEY("DICT:VERSION"),

	/** 字典： 商品供应商 表 */
	DICT_GOODS_SUPPLIER_KEY("DICT:GOODS_SUPPLIER"),

	DICT_BRAND_KEY("DICT:GOODS_BRAND"),

	DICT_UNIT_KEY("DICT:GOODS_UNIT"),

	DICT_STORAGES_KEY("DICT:STORAGES"),

	DICT_CATE_TREE_KEY("DICT:CATE_TREE"),

	DICT_SYSPARAM_KEY("DICT:SYSPARAM_KEY"),

	/***/
	DICT_COUNTRY_KEY("DICT:COUNTRYS"),

                                           DICT_FREIGHT_CITY_KEY("DICT:FREIGHT_CITY"),

                                           DICT_FREIGHT_RULE_KEY("DICT:FREIGHT_RULE"),
	// ------------------------------DICT END
	// ---------------------------------------

	// ------------------------------order start
	// ---------------------------------------
	/** 虚拟订单编码 key */
	ORDER_VIRTUAL_CODE_KEY("PS_ORDERV_CODE_"),

	ORDER_ADDRESS_KEY("PS_ORDER_ADDRESS_KEY"),
	/** 订单拆单锁定队列 1小时后就会拆单 */
	ORDER_LOCK_QUEUE_KEY("ORDER_LOCK_QUEUE"),

	// ----------------------- index subject miniProgram --------------
	/** 商品上下架 首页数据 */

	// -------------SPU下架----------------------
	// 首页
	INDEX_GOODS_SPU_("INDEX_GOODS_SPU_"),
	// 小程序
	MINI_INDEX_GOODS_SPU_("MINI_INDEX_GOODS_SPU_"),
	// 专题
	SUBJECT_GOODS_SPU_("SUBJECT_GOODS_SPU_"),

	// ------ SKU下架 skuId唯一
	INDEX_GOODS_SKU_("INDEX_GOODS_SKU_"), MINI_INDEX_GOODS_SKU_("MINI_INDEX_GOODS_SKU_"),
	SUBJECT_GOODS_SKU_("SUBJECT_GOODS_SKU_"),



	INVOICE_TYPE_COMMENT("INVOICE_TYPE_COMMENT"),

	INVOICE_CONTENT_TYPE_COMMENT("INVOICE_CONTENT_TYPE_COMMENT"),

	INVOICE_CONTACT_COMMENT("INVOICE_CONTACT_COMMENT"),

	INVOICE_SEND_COMMENT("INVOICE_SEND_COMMENT"),

	INVOICE_OBJECT("INVOICE_OBJECT"),

	// --------------------------------order end----------------------

	// ------------------------------服务开关 start
	// ---------------------------------------
	/**
	 * 订单服务开关
	 */
	SERVICE_CLOSE_ORDER("PS_SERVICE_CLOSE_ORDER"),
	/**
	 * 订单服务-用户相关功能 开关
	 */
	SERVICE_CLOSE_ORDER_MEMBER("PS_SERVICE_CLOSE_ORDER_MEMBER"),
	/**
	 * 订单服务-支付功能 开关
	 */
	SERVICE_CLOSE_ORDER_PAY("PS_SERVICE_CLOSE_ORDER_PAY"),
	/**
	 * APP接口服务-支付功能 开关
	 */
	SERVICE_CLOSE_APP("PS_SERVICE_CLOSE_APP"),
	/**
	 * 用户接口服务-支付功能 开关
	 */
	SERVICE_CLOSE_USER("PS_SERVICE_CLOSE_USER"),
	// ------------------------------服务开关 end
	// ---------------------------------------

	// --------------------------------

	SYS_PARAMETER_KEY("PS_SYS_PARAMETER"),
	/** 用户用 */
	SYS_PARAM_USER_KEY("PS_SYS_PARAM_USER"),
	/** 佣金用 */
	SYS_PARAM_COMISSION_KEY("PS_SYS_PARAM_COMISSION"),
	/**
	 * 公司退货接收地址
	 */
	COMPANY_RETURN_ADDRESS("PS_RETURN_ADDRESS"),

	// 内部分佣请求白名单
	AUTH_COMMISION_WHITE_LIST("PS_AUTH_COMMISION_WHITE_LIST"),

	// ------------------------------workflow start
	// ---------------------------------------
	WF_STATUS_KEY("WF_STATUS_KEY"),
	/** 组合状态 */
	WF_GROUP_STATUS_KEY("WF_GROUP_STATUS_KEY"),
	// --------------------------------workflow end----------------------
	/**
	 * 文件
	 */
	FILE_PREFIX("MS_FILE_"),
	// ------------------------------------------佣金相关---------------------------------------------
	/**
	 * 邀请码
	 */
	INVITATION_CODE_PREFIX("PS_INVITATION_CODE_"), INVITATION_CODE_KEY("PS_INVITATION_CODE_KEY"),
	SHOPKEEPER_USER_ID_KEY("PS_SHOPKEEPER_USER_ID_KEY"),
	COMMISSION_HANDLER_ORDER_KEY("PS_COMMISSION_HANDLER_ORDER_KEY"),
	/**
	 * 店主信息
	 */
	COMMISSION_SHOPKEEPER_KEY("PS_COMMISSION_SHOPKEEPER_KEY"),
	COMMISSION_SHOPKEEPER_INVITCODE_KEY("PS_COMMISSION_SHOPKEEPER_INVITCODE_KEY"),
	/**
	 * 周分红获取奖励最低分数
	 */
	WEEK_BONUS_BASICS_REWARD_SCORE("WEEK_BONUS_BASICS_REWARD_SCORE"),
	/**
	 * 周分红获取活动规则图片地址
	 */
	WEEK_BONUS_RULE_IMG("WEEK_BONUS_RULE_IMG"), WEEK_BONUS_SCORE_TYPE("WEEK_BONUS_SCORE_TYPE"),
	/**
	 * 邀请用户注册地址
	 */
	WEEK_BONUS_INVITATION_USER_URL("WEEK_BONUS_INVITATION_USER_URL"),
	/**
	 * 签到标识
	 */
	WEEK_BONUS_SIGN_IN_FLAG("WEEK_BONUS_SIGN_IN_FLAG"),
	/**
	 * 第一次暂时周分红达标时候展示弹框
	 */
	WEEK_BONUS_FIRST_SHOW("WEEK_BONUS_FIRST_SHOW"),
	/**
	 * 备用金入账比例
	 */
	PETTY_CASH_ACCOUNTING_RATIO("PETTY_CASH_ACCOUNTING_RATIO"),
	/**
	 * 分享标题投
	 */
	WEEK_BONUS_SHARE_TITLE("WEEK_BONUS_SHARE_TITLE"),
	/**
	 * 用户中心
	 */
	COMMISSION_MY_USER_CENTER("COMMISSION_MY_USER_CENTER"), COMMISSION_MY_USER("COMMISSION_MY_USER"),
	SALES_PERFORMANCE_OF_CURRENT_MONTH("SALES_PERFORMANCE_OF_CURRENT_MONTH"),
	ITEM_SECRETARY_PROGRESS("ITEM_SECRETARY_PROGRESS"), ITEM_INVITATION_INFO("ITEM_INVITATION_INFO"),
	ITEM_POTENTIAL_SHOPKEEPER("ITEM_POTENTIAL_SHOPKEEPER"),
	// ------------------------------------------佣金相关---------------------------------------------
	// ------------------------------文章 start
	// ---------------------------------------
	ARTICLE_LIKE_USER("ARTICLE_LIKE_USER_"),

	// ------------------------------提现 start
	// ---------------------------------------
	/** 提现用户 **/
	DRAW_CASH_USER("DRAW_CASH_USER:"),

	/** 连连支付的配置 begin ===> **/
	LLP_PAYMENT_URL("LLP_PAYMENT_URL"),

	LLP_CONFIRM_PAYMENT_URL("LLP_CONFIRM_PAYMENT_URL"),

	LLP_PAYMENT_QUERY_URL("LLP_PAYMENT_QUERY_URL"),

	LLP_TRADER_ACCT_QUERY_URL("LLP_TRADER_ACCT_QUERY_URL"),

	LLP_PUBLIC_KEY_ONLINE("LLP_PUBLIC_KEY_ONLINE"),

	LLP_BUSINESS_PRIVATE_KEY("LLP_BUSINESS_PRIVATE_KEY"),

	LLP_OID_PARTNER("LLP_OID_PARTNER"),

	LLP_API_VERSION("LLP_API_VERSION"),

	LLP_SIGN_TYPE("LLP_SIGN_TYPE"),

	LLP_NOTIFY_URL("LLP_NOTIFY_URL"),

	LLP_QUERY_BANKCARD_URL("LLP_QUERY_BANKCARD_URL"),

	LLP_SERVICE_CHARGE("LLP_SERVICE_CHARGE"),

	LLP_BUSI_PARTNER("LLP_BUSI_PARTNER"),

	LLP_PAY_NOTIFY_URL("LLP_PAY_NOTIFY_URL"),

	LLP_PAY_CREATEBILL_URL("LLP_PAY_CREATEBILL_URL"),

	LLP_FRMS_WARE_CATEGORY("LLP_FRMS_WARE_CATEGORY"),

	LLP_CARD_DEFAULT_LOGO("LLP_CARD_DEFAULT_LOGO"),

	PAYMETHOD_LIST("PAYMETHOD_LIST"),

	/** <=== end 连连支付的配置 **/

	LLP_PAYMENT_FLAG("LLP_PAYMENT_FLAG"),

	EXPORT_COMMON_INSERT_URL("EXPORT_COMMON_INSERT_URL"),

	EXPORT_COMMON_UPDATE_URL("EXPORT_COMMON_UPDATE_URL"),

	/** 有盾三方验证的配置 begin ===> **/
	YD_CHECK_BANK_CARD_URL("YD_CHECK_BANK_CARD_URL"),

	YD_PUBLIC_KEY("YD_PUBLIC_KEY"),

	YD_SECRET_KEY("YD_SECRET_KEY"),

	YD_PRODUCT_CODE_0302("YD_PRODUCT_CODE_0302"),

	YD_PRODUCT_CODE_0401("YD_PRODUCT_CODE_0401"),

	BANK_LIST_DRAW_CASH("BANK_LIST_DRAW_CASH"),

	// ------------------------------推送 start
	// ---------------------------------------
	/**
	 * 用户礼包订单
	 **/
	GIFT_ORDER_BYUSER("GIFT_ORDER_BYUSER:"),
	/**
	 * 友盟消息发送
	 **/
	UM_MESSAGE_SEND("UM_MESSAGE_SEND:"),
	/**
	 * 友盟保存token flag
	 **/
	APP_PUSH_FLAG("APP_PUSH_FLAG:"),
	/**
	 * 友盟需要定时发送消息
	 **/
	UM_MESSAGE_TIMED("UM_MESSAGE_TIMED");

	/**
	 * 超时 redis key 后缀
	 */
	private static final String TIMEOUT_SUFFEX = "_PSOUT";

	/**
	 * key 前缀
	 */
	private String key;
	/**
	 * 后缀
	 */
	private String suffex;

	/**
	 * 多个后缀的数组
	 */
	private String[] suffexs;

	CommonKey() {
		this.key = this.name();
	}

	CommonKey(String key) {
		this.key = key;
	}

	/**
	 * 设置后缀
	 *
	 * @param suffex
	 * @return
	 */
	@Override
	public CommonKey setSuffex(String suffex) {
		this.suffex = suffex;
		return this;
	}

	/**
	 * 设置后缀
	 *
	 * @param suffex
	 * @return
	 */
	@Override
	public CommonKey setSuffex(Number suffex) {
		this.suffex = suffex == null ? null : String.valueOf(suffex);
		return this;
	}

	/**
	 * 设置后缀
	 *
	 * @param suffex
	 * @return
	 */
	@Override
	public CommonKey setSuffex(String suffex, String... suffexs) {
		this.suffex = suffex;
		this.suffexs = suffexs;
		return this;
	}


	/**
	 * 设置后缀
	 *
	 * @param suffex
	 * @return
	 */
	@Override
	public CommonKey setSuffex(Number suffex, String... suffexs) {
		this.suffex = suffex == null ? null : String.valueOf(suffex);
		this.suffexs = suffexs;
		return this;
	}

	/**
	 * 获取 key字符串
	 *
	 * @return
	 */
	@Override
	public String getKey() {
		if (this.suffex == null) {
			return key;
		}
		StringBuilder sb = new StringBuilder(key).append(suffex);
		if (suffexs != null && suffexs.length > 0) {
			for (String string : this.suffexs) {
				sb.append(string);
			}
		}
		return sb.toString();
	}

	/**
	 * 获取超时key
	 *
	 * @return
	 */
	@Override
	public String getTimeoutKey() {
		StringBuilder sb = new StringBuilder(key);
		if (this.suffex == null) {
			sb.append(TIMEOUT_SUFFEX);
			return sb.toString();
		}
		sb.append(suffex);
		if (suffexs != null && suffexs.length > 0) {
			for (String string : this.suffexs) {
				sb.append(string);
			}
		}
		sb.append(TIMEOUT_SUFFEX);
		return sb.toString();
	}
}
