package com.lukepeace.projects.nevyhodcore.criteria;

import com.lukepeace.projects.nevyhodcore.entity.QOrder;
import com.lukepeace.projects.nevyhodcore.util.OrderFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

@Component
public class OrderCriteria implements ICriteria<OrderFilter> {
    @Override
    public Predicate where(OrderFilter filter) {
        BooleanBuilder where = new BooleanBuilder();
        QOrder qData = QOrder.order;

        if (filter.getId() != null) {
            where.and(qData.id.eq(filter.getId()));
        }
        if (filter.getStatus() != null) {
            where.and(qData.status.eq(filter.getStatus()));
        }

        return where;
    }
}
