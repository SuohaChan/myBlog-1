package com.tree.service.impl;

import com.tree.domain.LoginUser;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;
import com.tree.service.BlogLoginService;
import com.tree.utils.BeanCopyUtils;
import com.tree.utils.JwtUtil;
import com.tree.utils.RedisCache;
import com.tree.vo.BlogUserLoginVo;
import com.tree.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class BlogLoginServiceImpl implements BlogLoginService {


    @Autowired
    //Spring Security 提供的认证管理器，用于对用户进行认证。
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        // Spring Security 中用于封装用户名和密码的认证令牌类
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword());
        //用 AuthenticationManager 的 authenticate 方法对用户进行认证。
        // 该方法会根据配置的认证策略（如从数据库中查询用户信息、验证密码等）对用户进行验证，并返回一个 Authentication 对象。
        //委托给AuthenticationProvider 如 DaoAuthenticationProvider
        //其会从配置的UserDetailsService中获取与用户名对应的UserDetails
        //Dao 还会用配置的PasswordEncoder 对令牌中的密码进行加密
        //加密后与UserDetails密码对比
        Authentication authenticate  = authenticationManager.authenticate(authenticationToken);
        if(Objects.isNull(authenticate )){
            throw new RuntimeException("用户名或密码错误");
        }
        //获取userid 生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userId);
        //把用户信息存入redis
        redisCache.setCacheObject("bloglogin:"+userId,loginUser);

        // 创建新的认证令牌，包含用户信息和权限
        UsernamePasswordAuthenticationToken newAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUser, null, null);
        // 将认证信息存入 SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(newAuthenticationToken);

        //把token和userinfo封装 返回
        //把User转换成UserInfoVo
        UserInfoVo userinfoVo = BeanCopyUtils.copyBean(loginUser.getUser(), UserInfoVo.class);
        //存放令牌和用户对象
        System.out.println("adfadfadsfa"+userinfoVo.toString());
        BlogUserLoginVo vo = new BlogUserLoginVo(jwt, userinfoVo);
        //返回用户基本信息 以及 token 令牌
        return ResponseResult.okResult(vo);
    }

    @Override
    public ResponseResult logout() {
        //获取token 解析获取userid
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        //获取userid
        Long userId = loginUser.getUser().getId();
        redisCache.deleteObject("bloglogin:"+userId);
        return ResponseResult.okResult();
    }
}
