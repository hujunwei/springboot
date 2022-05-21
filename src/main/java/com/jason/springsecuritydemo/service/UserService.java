package com.jason.springsecuritydemo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jason.springsecuritydemo.pojo.User;

import java.util.List;

/**
 * @Description: com.jason.springsecuritydemo.service
 */
public interface UserService extends IService<User> {
    User getUserByUsername(String username);

    List<String> selectAllRoleByUserId(Integer uid);

    List<String> selectPermissionsByUserId(Integer uid);
}
