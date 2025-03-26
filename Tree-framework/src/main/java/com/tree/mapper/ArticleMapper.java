package com.tree.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tree.domain.Article;
import org.apache.ibatis.annotations.Mapper;

import java.io.Serializable;

//继承自 BaseMapper<Article>。
// BaseMapper 是 MyBatis-Plus 框架提供的一个通用 Mapper 接口，它封装了大量常用的数据库操作方法，如插入、更新、删除、查询等。
// <Article> 是泛型参数，指定了该 Mapper 接口操作的实体类为 Article，
// 这意味着 ArticleMapper 接口将用于对 Article 实体类对应的数据库表进行操作。

//通过继承 BaseMapper<Article>，ArticleMapper 接口可以直接使用 BaseMapper 提供的各种通用方法，而无需手动编写大量的 SQL 语句。这些通用方法包括但不限于：
//插入操作：
//int insert(T entity)：向数据库中插入一条记录，参数 entity 是要插入的实体对象。
//更新操作：
//int updateById(T entity)：根据主键更新数据库中的记录，参数 entity 是包含更新信息的实体对象。
//删除操作：
//int deleteById(Serializable id)：根据主键删除数据库中的记录，参数 id 是主键值。
//查询操作：
//T selectById(Serializable id)：根据主键查询数据库中的一条记录，返回对应的实体对象。
//List<T> selectList(Wrapper<T> queryWrapper)：根据查询条件查询数据库中的多条记录，返回实体对象列表。

public interface ArticleMapper extends BaseMapper<Article> {
}
