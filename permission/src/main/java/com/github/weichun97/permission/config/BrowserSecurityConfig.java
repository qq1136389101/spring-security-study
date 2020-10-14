package com.github.weichun97.permission.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSucessHandler myAuthenticationSucessHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MyAuthenticationAccessDeniedHandler myAuthenticationAccessDeniedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单方式
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSucessHandler) // 处理登录成功
                .failureHandler(myAuthenticationFailureHandler) // 处理登录失败
                .and()
                .exceptionHandling()
                .accessDeniedHandler(myAuthenticationAccessDeniedHandler)
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require", "/login.html").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and().csrf().disable()
        ;

    }
}
