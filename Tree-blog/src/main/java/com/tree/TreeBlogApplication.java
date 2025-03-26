package com.tree;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@MapperScan("com.tree.mapper")
@SpringBootApplication
@EnableScheduling

//@MapperScan 是 MyBatis-Plus 或 MyBatis 框架提供的一个注解，
// 它的主要作用是自动扫描指定包路径下的所有 Mapper 接口，并将这些接口注册为 Spring 的 Bean，
// 这样在 Spring 应用中就可以通过依赖注入的方式使用这些 Mapper 接口来进行数据库操作

public class TreeBlogApplication {
    public static void main(String[] args) {
        SpringApplication.run(TreeBlogApplication.class,args);
    }
}
