package com.github.weichun97.sms.config;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * The type Sms authentication token.
 */
public class SmsAuthenticationToken extends AbstractAuthenticationToken implements Serializable {
    private static final long serialVersionUID = 6551651721878114510L;

    private final Object principal;
    private Object credentials;

    /**
     * Instantiates a new Sms authentication token.
     *
     * @param mobile the mobile
     */
    public SmsAuthenticationToken(Object mobile) {
        super((Collection)null);
        this.principal = mobile;
        this.setAuthenticated(false);
    }

    /**
     * Instantiates a new Sms authentication token.
     *
     * @param principal   the principal
     * @param authorities the authorities
     */
    public SmsAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        super.setAuthenticated(true);
    }

    public Object getCredentials() {
        return this.credentials;
    }

    public Object getPrincipal() {
        return this.principal;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        if (isAuthenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        } else {
            super.setAuthenticated(false);
        }
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.credentials = null;
    }
}
