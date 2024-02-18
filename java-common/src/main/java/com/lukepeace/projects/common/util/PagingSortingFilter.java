package com.lukepeace.projects.common.util;

import lombok.*;
import org.springframework.data.domain.Pageable;

@AllArgsConstructor @Getter @Setter @ToString @NoArgsConstructor
public class PagingSortingFilter {

    protected Pageable pageable;

}
