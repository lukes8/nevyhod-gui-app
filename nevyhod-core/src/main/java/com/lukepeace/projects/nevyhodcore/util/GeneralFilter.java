package com.lukepeace.projects.nevyhodcore.util;

import com.lukepeace.projects.common.util.PagingSortingFilter;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Pageable;

@Data
@AllArgsConstructor
public class GeneralFilter extends PagingSortingFilter {

    public GeneralFilter(Pageable pageable) {
        super(pageable);
    }
}
