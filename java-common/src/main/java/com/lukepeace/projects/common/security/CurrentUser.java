package com.lukepeace.projects.common.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class CurrentUser implements UserDetails {

    private String password;
    private String username;
    private boolean isEnabled;
    private Collection<? extends GrantedAuthority> authorities;
    private LocalDateTime lastLogin;
    private Set<String> availableRoles = new HashSet<>();

    public CurrentUser(String password, String username, boolean isEnabled, Collection<? extends GrantedAuthority> authorities) {
        this.password = password;
        this.username = username;
        this.isEnabled = isEnabled;
        this.authorities = authorities;
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
        return isEnabled;
    }

    @Override
    public boolean isAccountNonLocked() {
        return isEnabled;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return isEnabled;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    public LocalDateTime getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(LocalDateTime lastLogin) {
        this.lastLogin = lastLogin;
    }

    public Set<String> getAvailableRoles() {
        return availableRoles;
    }

    public void setAvailableRoles(Set<String> availableRoles) {
        this.availableRoles = availableRoles;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

}
