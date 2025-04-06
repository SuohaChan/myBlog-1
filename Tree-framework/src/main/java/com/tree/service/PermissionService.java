package com.tree.service;


import com.tree.domain.LoginUser;
import com.tree.utils.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 35238
 * @date 2023/8/9 0009 13:40
 */

@Service("ps")
public class PermissionService {

    /**
     * 判断当前用户是否具有permission
     * @param permission 要判断的权限
     * @return
     */
    public boolean hasPermission(String permission){
        //如果是超级管理员  直接返回true
        if(SecurityUtils.isAdmin()){
            return true;
        }

        LoginUser loginUser = SecurityUtils.getLoginUser();
        if (loginUser != null) {
            List<String> permissions = loginUser.getPermissions();
            if (permissions != null) {
                //contains方法是 'List集合官方' 提供的方法，返回值是布尔值，如果用户具有对应权限就返回true
                return permissions.contains(permission);
            }
        }
        return false;
    }
}