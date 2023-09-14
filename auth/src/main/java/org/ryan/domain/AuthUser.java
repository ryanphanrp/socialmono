package org.ryan.domain;

import lombok.Getter;
import org.ryan.application.dto.response.UserDto;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AuthUser implements UserDetails {
    @Getter
    private final Long userId;
    private final String username;
    private final String password;

    public AuthUser(Long userId, String email, String password) {
        this.userId = userId;
        this.username = email;
        this.password = password;
    }

    public static AuthUser of(UserDto user) {
        return new AuthUser(user.userId(), user.username(), user.password());
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    public boolean isCorrectPassword(String pwd) {
        return this.password.equals(pwd);
    }
}
