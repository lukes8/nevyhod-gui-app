package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.*;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ID")
    @SequenceGenerator(name = "SEQ_ID", allocationSize = 5)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "CATEGORY")
    private String category;
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "AMOUNT")
    private Integer amount;
    @Column(name = "STATUS")
    private Integer status;
}
