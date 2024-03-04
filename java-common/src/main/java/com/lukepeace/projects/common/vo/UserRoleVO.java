package com.lukepeace.projects.common.vo;

import lombok.Builder;
import lombok.Data;

@Builder @Data
public class UserRoleVO {
    private String role;
    private String description;
}
