package com.lukepeace.projects.nevyhodcore.criteria;

import com.lukepeace.projects.nevyhodcore.util.GeneralFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

@Component
public class GeneralCriteria implements ICriteria<GeneralFilter> {
    @Override
    public Predicate where(GeneralFilter filter) {
        BooleanBuilder where = new BooleanBuilder();
       // TODO

        return where;
    }
}
