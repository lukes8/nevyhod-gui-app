package com.lukepeace.projects.nevyhodcore.entity;

import com.lukepeace.projects.nevyhodcore.entity.pk.UserRolePk;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Entity(name = "USER_ROLE")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRole {
    @EmbeddedId
    private UserRolePk id;
}
