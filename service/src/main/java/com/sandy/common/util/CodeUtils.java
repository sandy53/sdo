package com.sandy.common.util;

import java.text.DecimalFormat;
import java.util.Random;

/**
 *  编号生成工具类
 * 
 * @author zhangyg
 * @version $Id: CodeUtils.java, v 0.1 2016年12月17日 上午10:41:47 zhangyg Exp $
 */
public final class CodeUtils {



    private static final String       DATE_FORMAT             = "yyyyMMddhhmmsss";

    private static Random             RANDOM;

    /**
     *  标记格式化
     */
    public static final DecimalFormat SIGN_FORMT              = new DecimalFormat("000000");
    /**
     * 格式化活动编码
     */
    public static final DecimalFormat ACT_FORMT               = new DecimalFormat("0000");
    /**
     * 格式化期数编码
     */
    public static final DecimalFormat PHA_FORMT               = new DecimalFormat("00");

    /**
     *  标记格式化格式化字符串
     */
    private static final String       FORMT_STRING            = "888888";

    /**
     *  标记格式化格式化字符串 前缀
     */
    private static final String       FORMT_STRING_PREFIX     = "0";

    /**
     *  标记长度
     */
    public static final int           SIGN_LENGTH             = 6;

    /**
     *  账户相关编号 长度
     */
    public static final int           SIGN_LENGTH_FOR_ACCOUNT = 9;

    /**
     *  CMB支付标记长度
     */
    public static final int           SIGN_LENGTH_PAY_CMB     = 4;

    /**
     *  余额支付标记长度
     */
    public static final int           SIGN_LENGTH_PAY_CMI     = 4;

    /**
     *  活动订单开头标记
     */
    public static final String        PREFIX_ACT              = "A";

    /**
     *  用户密码随机数长度
     */
    public static final int        USER_PASSWORD              = 6;

    private static final SnowflakeIdWorker idWorker                = new SnowflakeIdWorker(1, 0);

	private CodeUtils() {

	}



    /**
     * 是否是活动订单
     * 
     * @param orderSn
     * @return
     */
    public static boolean isActOrder(String orderSn) {
        return orderSn != null && orderSn.startsWith(PREFIX_ACT);
    }

    /**
     * 格式化标记
     * 
     * @return4  
     */
    public static String formatSign(long param) {
        String str = Long.toString(param);
        int length = str.length() - SIGN_LENGTH;
        if (length == 0) {
            return str;
        } else if (length < 0) {
            str = FORMT_STRING_PREFIX + str;
            return FORMT_STRING.substring(0, Math.abs(length) - 1) + str;
        } else {
            return str.substring(length, str.length());
        }
    }

    /**
     *  Twitter的雪花算法
     * 
     * @return
     */
    public static long getBySnowflake() {
        return idWorker.nextId();
    }

    /**
     * 获取指定长度(1位到9位)的随机数字字符串
     *          
     * @param length  大于1  小于9
     * @return
     */
    public static int generateRandomInt(int length) {
        length = length < 1 ? 1 : length;
        length = length > 9 ? 9 : length;
        int max = ((int) Math.pow(10, length)) - 1;
        int min = (int) Math.pow(10, length - 1);
        return (int) (Math.random() * (max - min) + min);
    }

	/**
	 * 获取指的随机数字
	 * 
	 * @param length 最大范围
	 * @return
	 */
    /*	public static int generateRandom(int length) {
        if (RANDOM == null) {
            RANDOM = new Random();
        }
        return RANDOM.nextInt(length);
    	}
    */


}
