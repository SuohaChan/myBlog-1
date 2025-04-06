package com.tree.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;
/**
 * 角色和菜单关联表(SysRoleMenu)表实体类
 *
 * @author makejava
 * @since 2025-04-05 21:06:59
 */
@SuppressWarnings("serial")
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_role_menu")
public class RoleMenu {
//角色ID@TableId
    private Long roleId;
//菜单ID@TableId
    private Long menuId;

    
}
