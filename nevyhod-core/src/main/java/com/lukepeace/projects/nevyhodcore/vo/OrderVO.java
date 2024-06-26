package com.lukepeace.projects.nevyhodcore.vo;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder @ToString @AllArgsConstructor @Setter @Getter @NoArgsConstructor
public class OrderVO {

    private Long id;
    private String title;
    private String userId;
    private Double price;
    private LocalDateTime createdDate;
    private Integer amount;
    private Integer status;
}
