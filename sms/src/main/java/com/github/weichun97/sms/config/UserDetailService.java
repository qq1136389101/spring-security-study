package com.github.weichun97.sms.config;

import com.github.weichun97.sms.pojo.MyUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserDetailService implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String mobile) throws UsernameNotFoundException {
        // 模拟一个用户，替代数据库获取逻辑
        MyUser user = new MyUser();
        user.setMobile(mobile);
        return user;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
