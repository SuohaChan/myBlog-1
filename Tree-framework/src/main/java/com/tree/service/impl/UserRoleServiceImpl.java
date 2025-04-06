package com.tree.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tree.domain.UserRole;
import com.tree.mapper.UserRoleMapper;
import com.tree.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * (UserRole)表服务实现类
 *
 * @author tree
 * @since 2025-04-05 22:22:59
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
