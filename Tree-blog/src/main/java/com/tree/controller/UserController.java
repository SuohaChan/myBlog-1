package com.tree.controller;

import com.tree.annotation.mySystemLog;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;
import com.tree.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询个人信息接口
     * @return 返回封装的响应结果
     */
    @GetMapping("/userInfo")
    @mySystemLog(xxbusinessName = "查询个人信息")
    //@Operation(summary = "查询个人信息", description = "用于获取当前用户的个人信息")
    public ResponseResult userInfo(){
        return userService.userInfo();
    }

    /**
     * 更新用户信息接口
     * @param user 包含要更新的用户信息的实体
     * @return 返回封装的响应结果
     */
    @PutMapping("/userInfo")
    @mySystemLog(xxbusinessName = "更新用户信息")
    public ResponseResult updateUserInfo(@RequestBody  User user){
        return userService.updateUserInfo(user);
    }

    /**
     * 用户注册接口
     * @param user 包含注册信息的用户实体
     * @return 返回封装的响应结果
     */
    @PostMapping("/register")
    @mySystemLog(xxbusinessName = "用户注册")
    public ResponseResult register(@RequestBody  User user){
        return userService.register(user);
    }
}