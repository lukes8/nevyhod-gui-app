package com.lukepeace.projects.nevyhodcore.criteria;

import com.lukepeace.projects.nevyhodcore.util.UserFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

@Component
public class UserCriteria implements ICriteria<UserFilter> {
    @Override
    public Predicate where(UserFilter filter) {
        BooleanBuilder where = new BooleanBuilder();
       // TODO

        return where;
    }
}
