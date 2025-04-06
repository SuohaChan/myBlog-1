package com.tree.handler.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.util.Date;
import com.tree.utils.SecurityUtils;

@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger logger = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    @Override
    // 只要对数据库执行了插入语句，那么就会执行到这个方法
    //需要获取当前用户的userId来填充createBy和updateBy字段
    public void insertFill(MetaObject metaObject) {
        Long userId = null;
        try {
            // 获取用户id
            userId = SecurityUtils.getUserId();
        } catch (Exception e) {
            logger.error("获取用户 ID 时发生异常", e);
            userId = -1L; // 如果异常了，就说明该用户还没注册，我们就把该用户的 userid 字段赋值为 -1
        }
        // 自动把下面四个字段新增了值。
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("createBy", userId, metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("updateBy", userId, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", new Date(), metaObject);
        Long userId;
        try {
            if (SecurityUtils.getAuthentication() != null) {
                userId = SecurityUtils.getUserId();
            } else {
                logger.info("未找到认证信息，使用默认用户 ID");
                userId = -1L;
            }
        } catch (Exception e) {
            logger.error("获取用户 ID 时发生异常", e);
            userId = -1L;
        }
        this.setFieldValByName("updateBy", userId, metaObject);
    }
}