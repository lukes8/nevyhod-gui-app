package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ItemService {

    List<ItemVO> findAll();

    ItemVO findById(Long id);

    void create(ItemVO item);

    ItemVO delete(Long id);
}
