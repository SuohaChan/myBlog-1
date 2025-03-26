package com.tree.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import javax.swing.*;

@Configuration
public class RedisConfig {
    //RedisConfig 类通过自定义 RedisTemplate 的序列化方式，使得 Redis 的键以字符串形式存储，
    // 值以 JSON 字符串形式存储，方便管理和处理复杂的 Java 对象。
    // 同时，通过 Spring 的配置机制，将配置好的 RedisTemplate 注册为 Spring Bean，方便在项目中进行依赖注入和使用。

    @Bean
    @SuppressWarnings(value = { "unchecked", "rawtypes" })
    // 抑制编译器的泛型类型检查警告，因为在使用 FastJsonRedisSerializer 时可能会涉及到一些泛型类型的不明确使用。
    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory)
    {
        //RedisConnectionFactory connectionFactory：
        //Spring 会自动注入一个 RedisConnectionFactory 实例，它负责创建与 Redis 服务器的连接。
        //通过该连接工厂与 Redis 服务器建立连接
        RedisTemplate<Object, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        FastJsonRedisSerializer serializer = new FastJsonRedisSerializer(Object.class);

        // 使用StringRedisSerializer来序列化和反序列化redis的key值
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(serializer);

        // Hash的key也采用StringRedisSerializer的序列化方式
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(serializer);

        template.afterPropertiesSet();
        return template;
    }
}