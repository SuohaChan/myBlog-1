package com.tree.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)//表示mySystemlog注解类会保持到runtime阶段
@Target(ElementType.METHOD)
public @interface mySystemLog {
    //为controller提供接口的描述信息，用于'日志记录'功能
    String xxbusinessName();
}
