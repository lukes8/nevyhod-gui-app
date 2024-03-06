package com.lukepeace.projects.nevyhodcore.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name = "ORDER_STATE")
@Data
public class OrderState {
    @Id
    private Long id;
    private String name;

}
