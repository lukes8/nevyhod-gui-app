package com.lukepeace.projects.nevyhodcore.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

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
}
