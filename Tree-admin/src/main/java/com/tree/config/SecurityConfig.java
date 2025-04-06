package com.tree.config;


import com.tree.filter.JwtAuthenticationTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)//权限控制
//此注解用于启用 Spring Security 的 Web 安全支持。
// 它会自动配置 Spring Security 的核心功能，并且将 Spring Security 的默认过滤器链作为一个 @Bean 发布，
// 使得应用程序能够使用 Spring Security 来保护 Web 资源。
public class SecurityConfig {

    @Autowired
    //注入我们在blog工程写的JwtAuthenticationTokenFilter过滤器
    private JwtAuthenticationTokenFilter jwtAuthenticationTokenFilter;

    @Autowired
    UserDetailsService userDetailsService; // 注入 UserDetailsService

    @Autowired
    // 注入认证失败处理器，会在认证失败时被调用
    AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    // 注入授权失败处理器，会在授权失败时被调用
    private AccessDeniedHandler accessDeniedHandler;

    // 创建密码编码器 Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        //BCrypt 算法是一种自适应的哈希算法，会自动生成盐值，能够有效防止彩虹表攻击，安全性较高。
        return new BCryptPasswordEncoder();
    }


    @Bean
    // 配置认证管理器，用于处理用户认证
    public AuthenticationManager authenticationManager(PasswordEncoder passwordEncoder) {
        // 创建一个基于 DAO 的认证提供者
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        // 设置密码编码器
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        // 这里需要注入 UserDetailsService，假设已经有一个 userDetailsService Bean
        authenticationProvider.setUserDetailsService(userDetailsService);
        return new ProviderManager(authenticationProvider);
    }

    @Bean
    //httpSecurity 配置 HTTP 请求安全的类
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 关闭 CSRF 保护 CSRF（Cross-Site Request Forgery，跨站请求伪造）
                .csrf(AbstractHttpConfigurer::disable)
                // 配置会话管理为无状态
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 配置请求授权规则
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        // 允许匿名访问登录接口
                        .requestMatchers("/login").anonymous()
//                        //注销接口需要认证才能访问
//                        .requestMatchers("/logout").authenticated()
//                        //个人信息接口必须登录后才能访问
//                        .requestMatchers("/user/userInfo").authenticated()
//                        .requestMatchers("/upload").authenticated()
                        // 其他请求允许所有访问
                        .anyRequest().permitAll()
                )
                // 配置异常处理，设置认证失败和授权失败的处理器
                .exceptionHandling(exception -> exception
                        .authenticationEntryPoint(authenticationEntryPoint)
                        .accessDeniedHandler(accessDeniedHandler)
                )
                 //禁用注销功能
                .logout(logout -> logout.disable())
                //将自定义的 JwtAuthenticationTokenFilter 过滤器添加到 UsernamePasswordAuthenticationFilter 之前，以便在进行用户名和密码认证之前先进行 JWT 认证。
                .addFilterBefore(jwtAuthenticationTokenFilter, UsernamePasswordAuthenticationFilter.class)
                // 配置跨域支持
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));
        return http.build();
    }

    // 配置跨域策略
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*")); // 允许所有来源
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS")); // 允许的请求方法
        configuration.setAllowedHeaders(Arrays.asList("*")); // 允许的请求头
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}