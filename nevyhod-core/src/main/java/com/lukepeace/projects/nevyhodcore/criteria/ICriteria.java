package com.lukepeace.projects.nevyhodcore.criteria;

import com.lukepeace.projects.common.util.PagingSortingFilter;
import com.querydsl.core.types.Predicate;

public interface ICriteria<F extends PagingSortingFilter> {

    Predicate where(F filter);
}
