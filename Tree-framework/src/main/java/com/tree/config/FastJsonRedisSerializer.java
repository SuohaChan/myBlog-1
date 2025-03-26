package com.tree.config;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import com.alibaba.fastjson.parser.ParserConfig;
import org.springframework.util.Assert;
import java.nio.charset.Charset;

/**
 * Redis使用FastJson序列化
 * @author 35238
 * @date 2023/7/22 0022 21:12
 */
//. 数据存储格式要求
//Redis 是一个基于内存的键值对数据库，它本身只能存储二进制数据。当我们要将 Java 对象（如自定义的实体类、集合等）存储到 Redis 中时，这些对象在 Java 中是以特定的内存结构存在的，不能直接存储到 Redis 里。因此，需要将这些对象序列化为二进制数据，以便 Redis 能够存储和处理。
//        2. 数据传输需求
//在分布式系统中，不同的服务节点可能会通过网络与 Redis 进行交互。在数据传输过程中，需要将数据转换为一种通用的、可以在网络中传输的格式。序列化可以将对象转换为字节流，方便在网络中传输；而反序列化则可以将接收到的字节流还原为原始的 Java 对象，以便程序进行处理。
//        3. 数据持久化
//Redis 支持数据持久化，即将内存中的数据保存到磁盘上，以便在重启后能够恢复数据。为了将对象保存到磁盘，需要将其序列化为字节流；在恢复数据时，再将字节流反序列化为对象。
public class FastJsonRedisSerializer<T> implements RedisSerializer<T>{

    public static final Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    private Class<T> clazz;

    static
    {
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz)
    {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException
    {
        if (t == null)
        {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException
    {
        if (bytes == null || bytes.length <= 0)
        {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);

        return JSON.parseObject(str, clazz);
    }


    protected JavaType getJavaType(Class<?> clazz)
    {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}