package com.tree.utils;

import com.tree.domain.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.*;
import java.util.Objects;

//在 Spring Security 框架下获取当前登录用户的相关信息，以及进行一些与用户身份相关的判断
//SecurityContextHolder 是 Spring Security 框架中的一个核心类，它主要用于存储当前线程的安全上下文信息（SecurityContext）
@Slf4j
public class SecurityUtils {

    public static LoginUser getLoginUser() {
        Authentication authentication = getAuthentication();
        if (Objects.nonNull(authentication) &&
                !(authentication instanceof AnonymousAuthenticationToken)) {
            return (LoginUser) authentication.getPrincipal();
        }
        return null;
    }

    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public static Boolean isAdmin() {
        LoginUser loginUser = getLoginUser();
        if (Objects.nonNull(loginUser)) {
            Long id = loginUser.getUser().getId();
            return id != null && 1L == id;
        }
        return false;
    }

    public static Long getUserId() {
        LoginUser loginUser = getLoginUser();
        if (Objects.nonNull(loginUser)) {
            return loginUser.getUser().getId();
        }
        return null;
    }
}
