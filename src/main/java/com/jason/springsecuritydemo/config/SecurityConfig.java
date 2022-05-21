package com.jason.springsecuritydemo.config;

import com.jason.springsecuritydemo.exception.CustomAccessDeniedExceptionHandler;
import com.jason.springsecuritydemo.security.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

/**
 * @Description: com.jason.springsecuritydemo.config
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private PersistentTokenRepository tokenRepository;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private CustomAccessDeniedExceptionHandler customAccessDeniedExceptionHandler;

    @Bean
    protected PasswordEncoder getPwdEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表单认证
        http.formLogin()
                .loginProcessingUrl("/login")   //当发现/login时认为是登录，需要执行 UserDetailsServiceImpl
                .successForwardUrl("/main")   //此处是post请求
                .failureForwardUrl("/fail")
                .loginPage("/login")
                .successHandler((request, response, authentication) -> {
                    //Principal 主体，存放了登录用户的信息
                    User user = (User) authentication.getPrincipal();
//                    System.out.println(user.getUsername());
//                    System.out.println(user.getPassword());//密码输出为null
//                    System.out.println(user.getAuthorities());
                    //重定向到百度。这只是一个示例，具体需要看项目业务需求
                    response.sendRedirect("/main");
                });

//        // url 拦截 -- 用config来配置授权
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()  //login不需要被认证
//                .antMatchers("/fail").permitAll()  // fail不需要被认证
//                .antMatchers("/demo").hasAnyAuthority("demo:update", "demo:delete")
//                .anyRequest()
//                .authenticated();  //所有的请求都必须被认证。必须登录后才能访问。

        // url 拦截 -- 用一个permission service的类授权
//        http.authorizeRequests()
//                .antMatchers("/login").permitAll()  //login不需要被认证
//                .antMatchers("/fail").permitAll()  // fail不需要被认证
//                .anyRequest().access("@permissionServiceImpl.hasPermission(request, authentication)");

        // url 拦截 -- 用注解授权
        http.authorizeRequests()
                .antMatchers("/login").permitAll()  //login不需要被认证
                .antMatchers("/fail").permitAll()  // fail不需要被认证
                .anyRequest().authenticated();

        http.rememberMe()
                .tokenValiditySeconds(120)//单位：秒
                .tokenRepository(tokenRepository)
                .userDetailsService(userDetailsServiceImpl);

        http.exceptionHandling().accessDeniedHandler(customAccessDeniedExceptionHandler);
        //http.exceptionHandling().accessDeniedPage("accessdenied"); // 前后端分离不用这种

        //关闭csrf防护
        http.csrf().disable();
    }


}
