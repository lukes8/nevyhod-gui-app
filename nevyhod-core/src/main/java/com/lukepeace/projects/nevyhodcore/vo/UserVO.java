package com.lukepeace.projects.nevyhodcore.vo;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class UserVO {
    private String email;
    private String name;
    private Boolean enabled = true;
    private Boolean expired;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdatedDate;
    private LocalDateTime lastLoginDate;
    private List<UserRoleVO> roles;
}
