package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.common.util.PagingSortingFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class UserFilter extends PagingSortingFilter {

    private Long id;
    private String email;
    private String name;
    private Boolean enabled;
    private Boolean expired;
    private LocalDateTime createdDate;
    private LocalDateTime lastUpdated;
    private LocalDateTime lastLogin;
    public UserFilter(Pageable pageable) {
        super(pageable);
    }
}
