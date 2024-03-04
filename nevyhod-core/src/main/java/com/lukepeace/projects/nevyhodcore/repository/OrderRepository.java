package com.lukepeace.projects.nevyhodcore.repository;

import com.lukepeace.projects.nevyhodcore.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>, QuerydslPredicateExecutor<Order> {

    List<Order> findAllById(Long id);

    List<Order> findAllByStatus(Integer status);
}
