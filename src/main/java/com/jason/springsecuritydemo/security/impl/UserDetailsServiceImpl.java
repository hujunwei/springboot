package com.jason.springsecuritydemo.security.impl;

import com.jason.springsecuritydemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: com.jason.springsecuritydemo.security.impl
 */

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.jason.springsecuritydemo.pojo.User user = userService.getUserByUsername(username);

        if (null == user) {
            throw new UsernameNotFoundException("Username not found");
        }

        List<String> roles = userService.selectAllRoleByUserId(user.getId());
        List<String> permissions = userService.selectPermissionsByUserId(user.getId());

        List<String> rolesNormalized = new ArrayList<>();
        roles.forEach(r -> rolesNormalized.add("ROLE_" + r));

        String roleCommaSeparated = String.join(",", rolesNormalized);
        String permissionCommaSeparated = String.join(",", permissions);

        System.out.println(roleCommaSeparated + "," + permissionCommaSeparated);

        UserDetails userDetails = new User(username, user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(roleCommaSeparated + "," + permissionCommaSeparated));

        return userDetails;
    }
}
