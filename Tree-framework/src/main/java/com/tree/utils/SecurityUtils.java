package com.tree.utils;

import com.tree.domain.LoginUser;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.*;

//在 Spring Security 框架下获取当前登录用户的相关信息，以及进行一些与用户身份相关的判断
//SecurityContextHolder 是 Spring Security 框架中的一个核心类，它主要用于存储当前线程的安全上下文信息（SecurityContext）
public class SecurityUtils {

    public static LoginUser getLoginUser() {
        return (LoginUser) getAuthentication().getPrincipal();
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        Long id = getLoginUser().getUser().getId();
        //不为空且为管理员
        return id != null && 1L == id;
    }

    public static Long getUserId() {
        return getLoginUser().getUser().getId();
    }
}
