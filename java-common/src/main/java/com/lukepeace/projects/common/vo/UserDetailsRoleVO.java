package com.lukepeace.projects.common.vo;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class UserDetailsRoleVO {
    private String role;
    private String description;
}
