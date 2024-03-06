package com.lukepeace.projects.nevyhodcore.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data @Builder @AllArgsConstructor @NoArgsConstructor
public class UserRolePk {
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
}
