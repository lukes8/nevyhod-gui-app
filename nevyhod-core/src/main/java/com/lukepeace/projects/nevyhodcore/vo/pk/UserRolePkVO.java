package com.lukepeace.projects.nevyhodcore.vo.pk;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor @NoArgsConstructor @Data @Builder
public class UserRolePkVO {
    @JsonIgnore
    private String email;
    private String name;
}
