package com.lukepeace.projects.common.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class UserDetailVO {
    private String username;
    private String password;
    private LocalDateTime lastLogin;
    private List<UserDetailsRoleVO> roles;
    private boolean isEnabled;
}
