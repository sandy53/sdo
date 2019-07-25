package com.sandy.common.redis.key;

/**
 * Redis key
 *      
 *         新加的key都可以加到CommonKey 枚举中
 *         
 *      
 *      2019-04-04 起转换为 接口，可让枚举类继承
 *   
 * @author sandy
 * @version $Id: RedisKey.java, v 0.1 2016年12月22日 下午3:36:56 sandy Exp $
 */
public interface RedisKey {

    /***
     * 
     * 
     * 
     * 
     *  
     *   禁止在此添加 key     要加加到   相应的枚举类中
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     * 
     */




    /**
    * 设置后缀
    * 
    * @param suffex
    * @return
    */
    RedisKey setSuffex(String suffex, String... suffexs);

    /**
     * 设置后缀
     * 
     * @param suffex
     * @return
     */
    RedisKey setSuffex(String suffex);

    /**
     *   后缀
     * 
     * @param suffex
     * @param suffexs
     * @return
     */
    RedisKey setSuffex(Number suffex, String... suffexs);

    /**
     * 设置后缀
     * 
     * @param suffex
     * @return
     */
    RedisKey setSuffex(Number suffex);

    /**
     * 获取实际 redis key值
     * 
     * @return
     */
    String getKey();

}
