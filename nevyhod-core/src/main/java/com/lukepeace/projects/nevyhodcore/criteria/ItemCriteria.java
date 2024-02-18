package com.lukepeace.projects.nevyhodcore.criteria;

import com.lukepeace.projects.nevyhodcore.entity.QItem;
import com.lukepeace.projects.nevyhodcore.util.ItemFilter;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import org.springframework.stereotype.Component;

@Component
public class ItemCriteria implements ICriteria<ItemFilter> {
    @Override
    public Predicate where(ItemFilter filter) {
        BooleanBuilder where = new BooleanBuilder();
        QItem item = QItem.item;

        if (filter.getId() != null) {
            where.and(item.id.eq(filter.getId()));
        }
        if (filter.getStatus() != null) {
            where.and(item.status.eq(filter.getStatus()));
        }

        return where;
    }
}
