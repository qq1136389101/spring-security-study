package com.github.weichun97.session.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    MyAuthenticationSucessHandler myAuthenticationSucessHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;
    @Autowired
    MySessionExpiredStrategy mySessionExpiredStrategy;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin() // 表单方式
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/login")
                .successHandler(myAuthenticationSucessHandler) // 处理登录成功
                .failureHandler(myAuthenticationFailureHandler) // 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require", "/login.html").permitAll()
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .sessionManagement()
                .invalidSessionUrl("/session/invalid") // Session失效后跳转到这个链接
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .expiredSessionStrategy(mySessionExpiredStrategy).and()
                .and().csrf().disable()

        ;

    }
}
