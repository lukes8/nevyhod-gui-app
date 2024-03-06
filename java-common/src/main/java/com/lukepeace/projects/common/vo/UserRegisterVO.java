package com.lukepeace.projects.common.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserRegisterVO {
    private String username;
    private String password;
    private String email;
    private String location;
    private String phone;
}
