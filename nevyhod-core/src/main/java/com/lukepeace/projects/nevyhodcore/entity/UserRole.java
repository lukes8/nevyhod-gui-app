package com.lukepeace.projects.nevyhodcore.entity;

import com.lukepeace.projects.nevyhodcore.entity.pk.UserRolePk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "USER_INFO_ROLE")
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserRole {
    @EmbeddedId
    private UserRolePk id;
}
