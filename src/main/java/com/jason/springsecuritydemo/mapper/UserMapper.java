package com.jason.springsecuritydemo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jason.springsecuritydemo.pojo.User;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Description: com.jason.springsecuritydemo.mapper
 */

@Component
public interface UserMapper extends BaseMapper<User> {
    List<String> selectAllRoleByUserId(Integer uid);

    List<String> selectPermissionsByUserId(Integer uid);
}
