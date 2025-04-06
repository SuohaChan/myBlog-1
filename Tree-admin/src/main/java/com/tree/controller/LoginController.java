package com.tree.controller;


import com.tree.annotation.mySystemLog;
import com.tree.domain.LoginUser;
import com.tree.domain.Menu;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;
import com.tree.enums.AppHttpCodeEnum;
import com.tree.exception.SystemException;
import com.tree.service.MenuService;
import com.tree.service.RoleService;
import com.tree.service.SystemLoginService;
import com.tree.utils.BeanCopyUtils;
import com.tree.utils.SecurityUtils;
import com.tree.vo.AdminUserInfoVo;
import com.tree.vo.RoutersVo;
import com.tree.vo.UserInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
/**
 * @author 35238
 * @date 2023/8/3 0003 14:01
 */

@RestController
public class LoginController {

    @Autowired
    private SystemLoginService systemLoginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @mySystemLog(xxbusinessName = "用户后台登录")
    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return systemLoginService.login(user);
    }

    //---------------------------查询(超级管理员|非超级管理员)的权限和角色信息-----------------------------

    @GetMapping("/getInfo")
    @mySystemLog(xxbusinessName = "查询权限与角色信息")
    //AdminUserInfoVo是我们在huanf-framework工程写的类
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户。SecurityUtils是我们在huanf-framework写的类
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms = menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList = roleService.selectRoleKeyByUserId(loginUser.getUser().getId());

        //获取用户信息
        User user = loginUser.getUser();
        //BeanCopyUtils是我们在huanf-framework写的类
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);

        //封装响应返回
        AdminUserInfoVo adminUserInfoVo = new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    //-------------------------------------查询路由信息(权限菜单)--------------------------------------

    @GetMapping("/getRouters")
    @mySystemLog(xxbusinessName = "查看路由")
    //RoutersVo是我们在huanf-framework工程写的类
    public ResponseResult<RoutersVo> getRouters(){
        //获取用户id
        Long userId = SecurityUtils.getUserId();

        //根据用户id来查询menu(权限菜单)。要求查询结果是tree的形式，也就是子父菜单树
        List<Menu> menus = menuService.selectRouterMenuTreeByUserId(userId);
        //封装响应返回
        return ResponseResult.okResult(new RoutersVo(menus));
    }


    @PostMapping("/user/logout")
    public ResponseResult logout(){
        //退出登录
        return systemLoginService.logout();
    }
}