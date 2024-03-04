package com.lukepeace.projects.nevyhodcore.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder @ToString @AllArgsConstructor @Setter @Getter @NoArgsConstructor
public class ItemVO {

//    @JsonUnwrapped
//    private ItemIdVO id2;

    @JsonProperty("ID") private Long id;
    @JsonProperty("TITLE") private String title;
    private String category;
    private Double price;
    private LocalDateTime createdDate;
    private Integer amount;
    private Integer status;
    private Boolean disabled;
}
