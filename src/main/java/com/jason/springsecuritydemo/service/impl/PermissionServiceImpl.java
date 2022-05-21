package com.jason.springsecuritydemo.service.impl;

import com.jason.springsecuritydemo.service.PermissionService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * @Description: com.jason.springsecuritydemo.service.impl
 */

@Component
public class PermissionServiceImpl implements PermissionService {
    @Override
    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object obj = authentication.getPrincipal();
        if(obj instanceof UserDetails){
            UserDetails user = (UserDetails) obj;
            Collection<? extends GrantedAuthority> authorities = user.getAuthorities();

            System.out.println(authorities);
            System.out.println(request.getRequestURI());
            //return authorities.contains(new SimpleGrantedAuthority(request.getRequestURI()));

            return authorities.contains(new SimpleGrantedAuthority("demo:update"));
        }

        return false;
    }

}
