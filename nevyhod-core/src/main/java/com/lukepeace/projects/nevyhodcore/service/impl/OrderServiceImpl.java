package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.nevyhodcore.criteria.OrderCriteria;
import com.lukepeace.projects.nevyhodcore.entity.Order;
import com.lukepeace.projects.nevyhodcore.repository.OrderRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.OrderService;
import com.lukepeace.projects.nevyhodcore.util.OrderFilter;
import com.lukepeace.projects.nevyhodcore.vo.OrderVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl extends
        AbstractServiceImpl<Order, OrderVO, OrderRepository, Long, OrderFilter, OrderCriteria>
    implements OrderService {

}
