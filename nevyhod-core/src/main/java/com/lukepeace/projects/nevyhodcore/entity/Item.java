package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "ITEM")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ITEM_ID")
    @SequenceGenerator(name = "SEQ_ITEM_ID", allocationSize = 5)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    @Max(10)
    private Double price;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "STATUS")
    private Integer status;
    @Column(name = "ENABLED")
    private Boolean enabled;
}
