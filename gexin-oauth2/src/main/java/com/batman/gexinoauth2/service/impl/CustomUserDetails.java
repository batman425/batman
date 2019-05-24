package com.batman.gexinoauth2.service.impl;

import com.batman.gexinoauth2.model.User;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author liusongwei
 * @Title: CustomUserDetails
 * @ProjectName claimoauth
 * @Description: TODO
 * @date 2018/11/1615:15
 */
public class CustomUserDetails extends User implements UserDetails {
    private static final long serialVersionUID = 1702923242319850756L;

    private static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
    private final boolean enabled;
    private final boolean accountNonExpired;
    private final boolean credentialsNonExpired;
    private final boolean accountNonLocked;
    private final Set<GrantedAuthority> authorities;

    public CustomUserDetails(User user, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (user != null
                && !StringUtils.isBlank(user.getUsername())
                && !StringUtils.isBlank(user.getPassword())) {
            log.info("CustomUserDetails:" + user.getUsername());
            setUsername(user.getUsername());
            setPassword(user.getPassword());
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = Collections.unmodifiableSet(new HashSet<>(CollectionUtils.emptyIfNull(authorities)));
        } else {
            log.info("Cannot pass null or empty values to constructor:" + user.getUsername());
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public boolean isEnabled() {
        return enabled;
    }


    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }


    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }


    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }


    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }
}
