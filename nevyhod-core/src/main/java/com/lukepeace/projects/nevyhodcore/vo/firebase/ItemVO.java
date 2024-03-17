package com.lukepeace.projects.nevyhodcore.vo.firebase;

import lombok.*;

import java.util.Date;

@Data
@Builder @ToString @AllArgsConstructor @Setter @Getter @NoArgsConstructor
public class ItemVO {
    @Builder.Default
    private Long id = 0L;
    private String email;
    private String title;
    private String category;
    private String description;
    private Double price;
    private Date createdDate;
    private Integer amount;
    private Integer status;
    private Boolean disabled;   //special case; for more info see @ModelMapperConfig
}
