package com.lukepeace.projects.nevyhodcore.repository;

import com.lukepeace.projects.nevyhodcore.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>, QuerydslPredicateExecutor<Item> {

    List<Item> findAllById(Long id);

    List<Item> findAllByStatus(Integer status);
}
