package com.tree.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class BeanCopyUtils {

    private BeanCopyUtils() {
    }

    //单个实体类的拷贝 拷贝的对象 类的字节码对象
    public static <T> T copyBean(Object source, Class<T> clazz) {
        //创建目标对象
        T target;
        try {
            target = clazz.getDeclaredConstructor().newInstance();
            //实现属性拷贝，把source的属性拷贝到目标对象，BeanUtils是spring提供的工具
            //使用spring提供的BeanUtils类，来实现bean拷贝。第一个参数是源数据，第二个参数是目标数据，把源数据拷贝给目标数据
            BeanUtils.copyProperties(source, target);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return target;
    }

    //2、集合的拷贝 第一个参数是拷贝的集合 第二个是类的字节码对象
    public static <O,T> List<T> copyBeanList(List<O> list, Class<T> clazz) {
        return list.stream()
                .map(o -> copyBean(o,clazz))
                .collect(Collectors.toList());
    }
}

