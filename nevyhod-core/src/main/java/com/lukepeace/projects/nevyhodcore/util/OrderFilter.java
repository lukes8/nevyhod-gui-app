package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.common.util.PagingSortingFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class OrderFilter extends PagingSortingFilter {

    private Long id;
    private String title;
    private String userId;
    private Double price;
    private LocalDateTime createdDate;
    private Integer amount;
    private Integer status;
    public OrderFilter(Pageable pageable) {
        super(pageable);
    }
}
