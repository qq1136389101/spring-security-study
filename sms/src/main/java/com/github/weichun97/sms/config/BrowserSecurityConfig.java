package com.github.weichun97.sms.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    SmsCodeFilter smsCodeFilter;
    @Autowired
    SmsAuthenticationConfig smsAuthenticationConfig;
    @Autowired
    MyAuthenticationSucessHandler myAuthenticationSucessHandler;
    @Autowired
    MyAuthenticationFailureHandler myAuthenticationFailureHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(smsCodeFilter, UsernamePasswordAuthenticationFilter.class) // 添加验证码校验过滤器
                .formLogin() // 表单登录
                // http.httpBasic() // HTTP Basic
                .loginPage("/authentication/require") // 登录跳转 URL
                .loginProcessingUrl("/smsLogin") // 处理表单登录 URL
                .successHandler(myAuthenticationSucessHandler) // 处理登录成功
                .failureHandler(myAuthenticationFailureHandler) // 处理登录失败
                .and()
                .authorizeRequests() // 授权配置
                .antMatchers("/authentication/require",
                        "/login.html", "/code/image","/code/sms").permitAll() // 无需认证的请求路径
                .anyRequest()  // 所有请求
                .authenticated() // 都需要认证
                .and()
                .csrf().disable()
                .apply(smsAuthenticationConfig)
        ;
    }
}
