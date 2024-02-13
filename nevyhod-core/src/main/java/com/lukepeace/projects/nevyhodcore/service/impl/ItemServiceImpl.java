package com.lukepeace.projects.nevyhodcore.service.impl;

import com.lukepeace.projects.nevyhodcore.service.ItemService;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {


    List<ItemVO> dummyList() {

        List<ItemVO> lst = Arrays.asList(
                ItemVO.builder().id(1L).title("kosa").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build(),
                ItemVO.builder().id(2L).title("kosa2").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build(),
                ItemVO.builder().id(3L).title("kosa3").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build()

        );
        return lst;
    }

    @Override
    public List<ItemVO> findAll() {
        return dummyList();
    }

    @Override
    public ItemVO findById(Long id) {
        return null;
    }

    @Override
    public void create(ItemVO item) {

    }

    @Override
    public ItemVO delete(Long id) {
        return null;
    }
}
