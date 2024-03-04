package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.util.PagingSortingFilter;
import org.springframework.data.domain.Page;

public interface IService<VO, ID, F extends PagingSortingFilter> {
    Page<VO> findAll(F filter);

    VO create(VO objectVO, ID id) throws GeneralException;
}