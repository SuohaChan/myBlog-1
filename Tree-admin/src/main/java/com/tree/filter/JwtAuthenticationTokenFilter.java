package com.tree.filter;

import com.alibaba.fastjson.JSON;
import com.tree.domain.LoginUser;
import com.tree.domain.ResponseResult;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.utils.JwtUtil;
import com.tree.utils.RedisCache;
import com.tree.utils.WebUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;


/**
 * @author 35238
 * @date 2023/7/23 0023 13:24
 */
@Component
@Slf4j
//博客前台的登录认证过滤器。OncePerRequestFilter是springsecurity提供的类 用于拦截请求并验证 Token：
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {


    @Autowired
    //RedisCache是我们在huanf-framework工程写的工具类，用于操作redis
    private RedisCache redisCache;

    public JwtAuthenticationTokenFilter() {
        logger.info("JwtAuthenticationTokenFilter 实例被创建");
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 获取请求头中的token值
        String token = request.getHeader("token");
        if (!StringUtils.hasText(token)) {
            // 若请求头中没有，再尝试从查询参数中获取
            token = request.getParameter("token");
        }
        log.info("获取到的 Token: {}", token);

        // 判断是否拿到token值
        if (!StringUtils.hasText(token)) {
            log.info("请求头中未包含 Token，直接放行");
            // 说明该接口不需要登录，直接放行，不拦截
            filterChain.doFilter(request, response);
            return;
        }

        Claims claims = null;
        try {
            claims = JwtUtil.parseJWT(token);
        } catch (Exception e) {
            // 当token过期或token被篡改就会进入异常处理
            log.error("解析 Token 时出现异常: {}", e.getMessage(), e);
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }

        String userid = claims.getSubject();
        log.info("解析得到的用户 ID: {}", userid);

        if (redisCache == null) {
            log.error("redisCache 为 null，无法从 Redis 中获取用户信息");
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.SERVER_ERROR);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }else{
            log.info("redis成功加载");
        }

        // 在redis中，通过key来获取value，注意key我们是加过前缀的，取的时候也要加上前缀
        LoginUser loginUser = redisCache.getCacheObject("login:" + userid);
        // 如果在redis获取不到值，说明登录是过期了，需要重新登录
        if (Objects.isNull(loginUser)) {
            log.error("从 Redis 中未获取到用户信息，用户 ID: {}", userid);
            ResponseResult result = ResponseResult.errorResult(AppHttpCodeEnum.NEED_LOGIN);
            WebUtils.renderString(response, JSON.toJSONString(result));
            return;
        }else{
            log.info("从 Redis 中成功获取到用户信息，用户 ID: {}", userid);
        }

        // 把从redis获取到的value，存入到SecurityContextHolder(Security官方提供的类)
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, null);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
