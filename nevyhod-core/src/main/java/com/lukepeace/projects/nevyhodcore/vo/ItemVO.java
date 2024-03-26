package com.lukepeace.projects.nevyhodcore.vo;

import lombok.*;

import java.time.LocalDateTime;

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
    private LocalDateTime createdDate;
    private Integer amount;
    private Integer status;
    private Boolean disabled;   //special case; for more info see @ModelMapperConfig
    private String imagePath;
}
