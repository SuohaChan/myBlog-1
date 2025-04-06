package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.RoleMenu;


/**
 * 角色和菜单关联表(SysRoleMenu)表实体类(RoleMenu)表服务接口
 *
 * @author tree
 * @since 2025-04-05 21:31:10
 */
public interface RoleMenuService extends IService<RoleMenu> {
    //修改角色-保存修改好的角色信息
    void deleteRoleMenuByRoleId(Long id);
}
