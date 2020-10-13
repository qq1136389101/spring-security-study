package com.github.weichun97.sms.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Set;

@Data
public class MyUser implements UserDetails,Serializable {
    private static final long serialVersionUID = 3497935890426858541L;

    private String mobile;

    private String username;

    private String password;

    private Set<GrantedAuthority> authorities;

    private boolean accountNonExpired = true;

    private boolean accountNonLocked= true;

    private boolean credentialsNonExpired= true;

    private boolean enabled= true;
}
