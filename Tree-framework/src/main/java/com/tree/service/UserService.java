package com.tree.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tree.domain.ResponseResult;
import com.tree.domain.User;

public interface UserService extends IService<User> {
    ResponseResult userInfo();

    ResponseResult updateUserInfo(User user);

    ResponseResult register(User user);
}
