package com.lukepeace.projects.nevyhodcore.entity.pk;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.*;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@ToString
public class UserRolePk {
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "NAME")
    private String name;
}
