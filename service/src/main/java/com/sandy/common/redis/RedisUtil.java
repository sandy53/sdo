package com.sandy.common.redis;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ZSetOperations;

import com.sandy.common.Assert;
import com.sandy.common.exception.RuningException;
import com.sandy.common.model.EmptyModel;
import com.sandy.common.redis.key.RedisKey;



/**
 *   redis 工具类
 * 
 * @author zhang
 * @version $Id: RedisUtil.java, v 0.1 2018年4月27日 上午9:48:20 zhang Exp $
 */
public final class RedisUtil<T> {
    //private Logger                   logger = LoggerFactory.getLogger(this.getClass());

    private static final Random      RANDOM = new Random();

    private RedisTemplate<String, T> redisTemplate;
    private HashOperations<String, String, T> hashTemplate;
    private SetOperations<String, T>          setTemplate;
    private ZSetOperations<String, T>         zSetTemplate;

    public RedisUtil(RedisTemplate<String, T> redisTemplate) {
        if (redisTemplate == null) {
            throw new RuningException("RedisUtil<Object>init fail,  redisTemplate is null!");
        }
        this.redisTemplate = redisTemplate;
        this.hashTemplate = this.redisTemplate.opsForHash();
        this.setTemplate = redisTemplate.opsForSet();
        this.zSetTemplate = redisTemplate.opsForZSet();
    }

    /**
     * 超时设置
     * 
     * @param redisKey
     * @param date
     * @return
     */
    public boolean expire(RedisKey redisKey, Date date) {
        Assert.notNull(redisKey);
        Assert.notNull(date);
        Boolean flag = this.redisTemplate.expireAt(redisKey.getKey(), date);
        return flag != null && flag;
    }


    /**
     * 超时设置
     *
     * @param redisKey
     * @param timeout
     * @param timeUnit
     * @return
     */
    public boolean expire(RedisKey redisKey, long timeout, TimeUnit timeUnit) {
        Assert.notNull(redisKey);
        Assert.notNull(timeUnit);
        Boolean flag = this.redisTemplate.expire(redisKey.getKey(), timeout, timeUnit);
        return flag != null && flag;
    }

    /**
     * 递增   步进值为1
     * 
     * @param redisKey
     * @return
     */
    public Long increment(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return increment(redisKey.getKey(), 1L);
    }


    /**
     * 递增
     * 
     * @param redisKey
     * @param delta    步进值
     * @return
     */
    private Long increment(String redisKey, long delta) {
        Assert.notNull(redisKey);
        return redisTemplate.opsForValue().increment(redisKey, delta);
    }

    /**
     * 递增
     * 
     * @param redisKey
     * @param delta
     * @return
     */
    public Long increment(RedisKey redisKey, long delta) {
        Assert.notNull(redisKey);
        return increment(redisKey.getKey(), delta);
    }

    public boolean exists(RedisKey key) {
        return this.exists(key.getKey());
    }

    /**
     *  使用枚举
     * 
     * @param key
     * @return
     */
    @Deprecated
    public boolean exists(String key) {
        Boolean flag = this.redisTemplate.hasKey(key);
        return flag != null && flag;
    }

    /**
     *  HASH  累加
     * 
     * @param redisKey
     * @param key
     * @param init    累加值
     * @return
     */

    private int hashIncrement(String redisKey, String key, int init) {
        Assert.notNull(redisKey);
        Assert.notEmpty(key);
        return hashTemplate.increment(redisKey, key, init).intValue();
    }

    /**
     * 获取hash的所有key
     *
     * @param redisKey
     * @return
     */
    public Set<String> hashKeys(String redisKey) {
        Assert.notNull(redisKey);
        return hashTemplate.keys(redisKey);
    }

    /**
     * 获取累加的值   
     *    注意： 此方法只获取不会累加
     * @param redisKey
     * @param key
     * @return
     */
    public int hashIncrementValue(RedisKey redisKey, String key) {
        return hashIncrement(redisKey.getKey(), key, 0);
    }

    public int hashIncrement(RedisKey redisKey, String key, Integer init) {
        Assert.notNull(redisKey);
        Assert.notEmpty(key);
        return hashTemplate.increment(redisKey.getKey(), key, init).intValue();
    }

    public int hashIncrement(RedisKey redisKey, String key, Long init) {
        Assert.notNull(redisKey);
        Assert.notEmpty(key);
        return hashTemplate.increment(redisKey.getKey(), key, init).intValue();
    }

    public Cursor<Entry<String, T>> hashScan(RedisKey redisKey) {
        return hashTemplate.scan(redisKey.getKey(),
            ScanOptions.scanOptions().build());
    }

    public boolean hashHaskey(RedisKey redisKey, Object key) {
        Assert.notNull(redisKey);
        Assert.notNull(key);
        return hashTemplate.hasKey(redisKey.getKey(), Objects.toString(key));
    }





    public void hashDelete(String redisKey, String key) {
        Assert.notNull(redisKey);
        Assert.notEmpty(key);
        hashTemplate.delete(redisKey, key);
    }

    public <HK> void hashDelete(RedisKey redisKey, HK key) {
        hashDelete(redisKey.getKey(), key.toString());
    }

    public void hashDelete(RedisKey redisKey, Object... keys) {
        Assert.notNull(redisKey);
        if (keys != null && keys.length != 0) {
            hashTemplate.delete(redisKey.getKey(), keys);
        }
    }
    public Map<String, T> hash(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return hashTemplate.entries(redisKey.getKey());
    }

    public List<T> hashValues(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return hashTemplate.values(redisKey.getKey());
    }


    public <HK> T hash(RedisKey redisKey, HK key) {
        Assert.notNull(redisKey);
        Assert.notNull(key);
        return hashTemplate.get(redisKey.getKey(), key.toString());
    }

    public List<T> hashMultiGet(RedisKey redisKey, Collection<String> keys) {
        Assert.notNull(redisKey);
        Assert.notNull(keys);
        return hashTemplate.multiGet(redisKey.getKey(), keys);
    }

    public void hashAddAll(RedisKey redisKey, Map<String, T> map) {
        Assert.notNull(redisKey);
        hashTemplate.putAll(redisKey.getKey(), map);
    }

    public <HK> void hash(RedisKey redisKey, HK key, T value) {
        Assert.notNull(redisKey);
        Assert.notNull(key);
        hashTemplate.put(redisKey.getKey(), key.toString(), value);
    }




    //  redis 消息  ---------------------------------------------------------- START ------------------------------------

    /**
     * redis 消息发送
     * 
     * @param channel
     * @param message
     */
    public void sendMsg(RedisChannel channel, Object message) {
        Assert.notNull(channel, "redis sendMsg  channel is null");
        Assert.notNull(message, "redis sendMsg  message is null");
        // logger.debug("redis-sendMsg : {} -> {} ", channel, message);
        redisTemplate.convertAndSend(channel.name(), message);
    }

    //  redis 消息  ---------------------------------------------------------- END ------------------------------------


    @SuppressWarnings("unchecked")
    public Long hset(RedisKey redisKey, T value) {
        Assert.notNull(redisKey);

        return setTemplate.add(redisKey.getKey(), value);
    }


    public Boolean hsetExists(RedisKey redisKey, T value) {
        Assert.notNull(redisKey);
        return setTemplate.isMember(redisKey.getKey(), value);
    }



    public Long hsetDelete(RedisKey redisKey, T value) {
        Assert.notNull(redisKey);
        return setTemplate.remove(redisKey.getKey(), value);
    }

    public T hset(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return setTemplate.pop(redisKey.getKey());
    }


    
    public long zsetSize(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return zSetTemplate.size(redisKey.getKey());
    }
    
    public boolean zsetAdd(RedisKey redisKey, T value, double score) {
        Assert.notNull(redisKey);
        return zSetTemplate.add(redisKey.getKey(), value, score);
    }
    
    
    public Set<T> zsetReverseRange(RedisKey redisKey, long start, long end) {
        Assert.notNull(redisKey);
        return zSetTemplate.reverseRange(redisKey.getKey(), start, end);
    }
    


    /**
     * 队列添加
     * 
     * @param value
     */
    public Long leftPush(RedisKey redisKey, T value) {
        return redisTemplate.opsForList().leftPush(redisKey.getKey(), value);
    }

    public Long rightPush(RedisKey redisKey, T value) {
        return redisTemplate.opsForList().rightPush(redisKey.getKey(), value);
    }
    /**
     * 队列取出 
     */
    public T rightPop(RedisKey redisKey) {
        return redisTemplate.opsForList().rightPop(redisKey.getKey());
    }

    /**
     * 取出指定索引的值
     * 
     * @param redisKey
     * @param index
     * @return
     */
    public T listIndex(RedisKey redisKey, long index) {
        return listIndex(redisKey.getKey(), index);
    }

    public T listIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     *   默认有效期为一天
     * 
     * @param key
     * @param value
     */
    /*    public void set(RedisKey redisKey, T value) {
        this.set(redisKey.getKey(), value);
    }
    */
    /**
     *   默认有效期为一天
     * 
     * @param key
     * @param value
     */
    @Deprecated
    public void set(String key, T value) {
        redisTemplate.opsForValue().set(key, value, getTimeout(RedisTimeOut.S_86400),
            TimeUnit.SECONDS);
    }

    /**
     *  超时时间为0时， 为永久
     *   
     * @param key
     * @param value
     * @param timeout  超时时间，单位秒； 如果传入0， 则表示无限制 
     */
    /*    public void set(RedisKey redisKey, T value, long timeout) {
        if (timeout == 0) {
            redisTemplate.opsForValue().set(redisKey.getKey(), value);
        } else {
            redisTemplate.opsForValue().set(redisKey.getKey(), value, timeout, TimeUnit.SECONDS);
        }
    }*/

    /**
     * 
     * 
     * @param key
     * @param value
     * @param timeout    超时时间
     */
    public void set(RedisKey key, T value, long timeout) {
        this.set(key.getKey(), value, timeout);
    }

    public void set(RedisKey key, T value) {
        this.set(key.getKey(), value, RedisTimeOut.S_7200);
    }

    /**
     *  给key设置 一个空模型
     * 
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void setEmpty(RedisKey key) {
        Assert.notNull(key);
        redisTemplate.opsForValue().set(key.getKey(), (T) EmptyModel.empty(),
            getTimeout(RedisTimeOut.S_7200), TimeUnit.SECONDS);
    }

    /**
     * 
     * 
     * @param key
     * @param value
     * @param timeout    超时时间 秒
     */
    @Deprecated
    public void set(String key, T value, long timeout) {
        redisTemplate.opsForValue().set(key, value, getTimeout(timeout), TimeUnit.SECONDS);
    }

    /**
     * 
     * 
     * @param key
     * @param value
     * @param timeout    超时时间
     * @param timeUnit   超时时间单位
     */
    public void set(RedisKey key, T value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key.getKey(), value, getTimeout(timeout), timeUnit);
    }

    /**
     * 获取
     * 
     * @param key
     * @return
     */
    /*    public T get(RedisKey redisKey) {
        Assert.notNull(redisKey);
        return get(redisKey.getKey());
    }*/

    /**
     * 获取
     * 
     * @param key
     * @return
     */
    public T get(String key) {
        Assert.notNull(key);
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 获取
     * 
     * @param key
     * @return
     */
    public T get(RedisKey key) {
        Assert.notNull(key);
        return get(key.getKey());
    }


    public Boolean delete(String key) {
        Assert.notEmpty(key);
        return redisTemplate.delete(key);
    }

    public Boolean delete(RedisKey key) {
        Assert.notNull(key);
        return delete(key.getKey());
    }

    /**
     * 模糊删除
     * @param prex
     */
    public Long deleteByPrex(String prex) {
        Set<String> keys = redisTemplate.keys(prex);
        if (CollectionUtils.isNotEmpty(keys))
            return redisTemplate.delete(keys);
        return null;
    }

    /**
     * 范围检索,返回List
     * @param start
     * @param end
     * @return
     */
    public List<T> range(RedisKey redisKey, long start, long end) {
        return redisTemplate.opsForList().range(redisKey.getKey(), start, end);
    }

    /**
     * 队列获取数量
     * 
     * @param redisKey
     * @return
     */
    public Long listSize(RedisKey redisKey) {
        return redisTemplate.opsForList().size(redisKey.getKey());
    }


    /**
     *  获取1-1000的随机数  用于加在超时时间后面，防止缓存在同一时间失效
     * 
     * @param second    超时的秒数
     * @return
     */
    private long getTimeout(long second) {
        return second + RANDOM.nextInt(1000) + 1;
    }

}
