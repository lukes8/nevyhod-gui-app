package com.lukepeace.projects.common.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data @Builder
public class UserVO {
    private String username;
    private String password;
    private LocalDateTime lastLogin;
    private List<UserRoleVO> roles;
    private boolean isEnabled;
}
