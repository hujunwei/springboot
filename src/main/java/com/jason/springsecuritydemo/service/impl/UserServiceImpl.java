package com.jason.springsecuritydemo.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jason.springsecuritydemo.mapper.UserMapper;
import com.jason.springsecuritydemo.pojo.User;
import com.jason.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description: com.jason.springsecuritydemo.service.impl
 */

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User getUserByUsername(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        com.jason.springsecuritydemo.pojo.User user = super.getOne(queryWrapper);
        return user;
    }

    @Override
    public List<String> selectAllRoleByUserId(Integer uid) {
        return userMapper.selectAllRoleByUserId(uid);
    }

    @Override
    public List<String> selectPermissionsByUserId(Integer uid) {
        return userMapper.selectPermissionsByUserId(uid);
    }
}
