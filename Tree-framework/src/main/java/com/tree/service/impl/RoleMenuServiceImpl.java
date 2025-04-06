package com.tree.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.domain.RoleMenu;
import com.tree.mapper.RoleMenuMapper;
import com.tree.service.RoleMenuService;
import org.springframework.stereotype.Service;

/**
 * 角色和菜单关联表(SysRoleMenu)表实体类(RoleMenu)表服务实现类
 *
 * @author tree
 * @since 2025-04-05 21:31:10
 */
@Service("roleMenuService")
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements RoleMenuService {
    @Override
    //修改角色-保存修改好的角色信息
    public void deleteRoleMenuByRoleId(Long id) {
        LambdaQueryWrapper<RoleMenu> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(RoleMenu::getRoleId,id);
        remove(queryWrapper);
    }
}
