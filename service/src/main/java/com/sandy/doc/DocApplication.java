package com.sandy.doc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.transaction.annotation.Transactional;

import com.sandy.common.redis.RedisUtil;

@Transactional
@MapperScan("com.sandy.record.dao")
@SpringBootApplication(scanBasePackages = { "com.sandy" })
public class DocApplication {

    public static void main(String[] args) {
        SpringApplication.run(DocApplication.class, args);
    }

    /** redis 库   */
    protected int database = 11;
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringSerializer);
        redisTemplate.setHashKeySerializer(stringSerializer);
        if (redisConnectionFactory instanceof LettuceConnectionFactory) {
            LettuceConnectionFactory lettuceConnectionFactory = (LettuceConnectionFactory) redisConnectionFactory;
            lettuceConnectionFactory.setDatabase(database);
            redisTemplate.setConnectionFactory(redisConnectionFactory);
            return redisTemplate;
        }
        throw new RuntimeException("unsuport redis connection factory! " + redisConnectionFactory);
    }

    @Bean
    public RedisUtil<?> redisUtil(RedisTemplate<String, Object> redisTemplate) {
        return new RedisUtil<>(redisTemplate);
    }

}
