
package com.sandy.common.redis.key;

/**
 * 能用redisKey
 *
 * @author zhangyg
 * @version $Id: CommonKey.java, v 0.1 2019年1月31日 上午11:41:44 zhangyg Exp $
 */
public enum CommonKey implements RedisKey {
	AA_TEST_KEY,

                                           //user-----------------------------------------------------------start

                                           USER_VERIFY_CODE("USER:VCODE_"),
                                           
                                           
                                           /** 认证： token 对应的 用户编码 */
                                           AUTH_TOKEN_USER_PREFIX("AUTH:TOKEN_USER_"),

                                           /** 认证： 刷新token 对应的 用户编码 */
                                           AUTH_TOKEN_REFRESH_PREFIX("AUTH:TOKEN_R_USER_"),

                                           /** 认证： 用户信息 */
                                           AUTH_USER_KEY("AUTH:USER"),

                                           //user-----------------------------------------------------------end
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


	// ----------------------------------
    ;



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


}
