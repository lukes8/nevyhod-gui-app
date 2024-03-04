package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Entity(name = "ORDER_HEADER")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@ToString
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORDER_HEADER_ID")
    @SequenceGenerator(name = "SEQ_ORDER_HEADER_ID", allocationSize = 5)
    @Column(name = "ID")
    private Long id;
    @Column(name = "TITLE")
    private String title;
    @Column(name = "USER_ID")
    @NotBlank
    private String userId;
    @Min(0) @Max(100000)
    @Column(name = "PRICE")
    private Double price;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Max(10)
    @Column(name = "AMOUNT")
    private Integer amount;

    //@Check or Pattern 0|1|2|3|4
    @Column(name = "STATUS")
    private Integer status;
}
