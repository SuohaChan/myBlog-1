package com.tree.service;

import com.tree.domain.ResponseResult;
import com.tree.domain.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
