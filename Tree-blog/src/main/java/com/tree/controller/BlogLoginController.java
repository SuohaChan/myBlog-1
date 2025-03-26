package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;
import com.tree.service.BlogLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BlogLoginController {
    @Autowired
    //BlogLoginService是我们在service目录写的接口
    private BlogLoginService blogLoginService;

    @PostMapping("/login")
    @mySystemLog(xxbusinessName = "用户登录")
    public ResponseResult login(@RequestBody User user){
        //如果用户在进行登录时，没有传入'用户名'
//        if(!StringUtils.hasText(user.getUserName())){
//            // 提示'必须要传用户名'。AppHttpCodeEnum是我们写的枚举类。SystemException是我们写的统一异常处理的类
//            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
//        }
        return blogLoginService.login(user);
    }
    @PostMapping("/logout")
    @mySystemLog(xxbusinessName = "用户登出")
    public ResponseResult logout(){
        return blogLoginService.logout();
    }
}
