package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import java.time.LocalDateTime;

@Entity(name = "ITEM")
public class Item {

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
