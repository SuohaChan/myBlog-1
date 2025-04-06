package com.tree.vo;

import com.tree.domain.Role;
import com.tree.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/10 0010 21:36
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserInfoAndRoleIdsVo {
    //用户
    private User user;
    //所有角色表
    private List<Role> roles;
    //该用户所用有的角色
    private List<Long> roleIds;
}