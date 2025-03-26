package com.tree.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tree.domain.Menu;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/4 0004 13:23
 */
public interface MenuMapper extends BaseMapper<Menu> {
    //查询普通用户的权限信息
    List<String> selectPermsByOtherUserId(Long userId);
    //查询超级管理员的路由信息(权限菜单)
    List<Menu> selectAllRouterMenu();
    //查询普通用户的路由信息(权限菜单)
    List<Menu> selectOtherRouterMenuTreeByUserId(Long userId);
}