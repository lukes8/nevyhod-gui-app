package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "USER_PENDING")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserPending {
    @Id
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
    @NotNull
    @Column(name = "ENABLED")
    private Boolean enabled;
    @Column(name = "EXPIRED")
    private Boolean expired;
    @NotNull
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "LAST_UPDATED_DATE")
    private LocalDateTime lastUpdatedDate;
    @Column(name = "LAST_LOGIN_DATE")
    private LocalDateTime lastLoginDate;
}
