package com.lukepeace.projects.nevyhodcore.vo;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ItemVO {
    private Long id;
    private String title;
    private String category;
    private Double price;
    private LocalDateTime createdDate;
    private Integer amount;
    private Integer status;
}
