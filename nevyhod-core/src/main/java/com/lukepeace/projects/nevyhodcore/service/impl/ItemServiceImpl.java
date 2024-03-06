package com.lukepeace.projects.nevyhodcore.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.criteria.ItemCriteria;
import com.lukepeace.projects.nevyhodcore.entity.Item;
import com.lukepeace.projects.nevyhodcore.repository.ItemRepository;
import com.lukepeace.projects.nevyhodcore.service.AbstractServiceImpl;
import com.lukepeace.projects.nevyhodcore.service.ItemService;
import com.lukepeace.projects.nevyhodcore.util.ItemFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class ItemServiceImpl extends
        AbstractServiceImpl<Item, ItemVO, ItemRepository, Long, ItemFilter, ItemCriteria>
    implements ItemService {

    @Autowired
    private ItemRepository itemRepository;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelMapper modelMapper;

    final List<ItemVO> dummyList() {

        List<ItemVO> lst = Arrays.asList(
                ItemVO.builder().id(1L).title("kosa").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build(),
                ItemVO.builder().id(2L).title("kosa2").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build(),
                ItemVO.builder().id(3L).title("kosa3").category("Zahrada").amount(1).price(0.0).status(0).createdDate(LocalDateTime.now()).build()

        );
        return lst;
    }

    @Override
    public List<ItemVO> findAll() throws GeneralException {

        log.debug("Green debug test");
        this.validateExistence();
        List<Item> lst = itemRepository.findAll();
        List<ItemVO> lstVO = this.map2VO(lst, ItemVO.class);
        return lstVO;
    }

//    @Override
//    public Page<ItemVO> findAll(ItemFilter filter) {
//
//        Page<ItemVO> all = super.findAll(filter);
//        return all;
//    }

    @Override
    public ItemVO findById(Long id) {
        return null;
    }

//    @Override
//    public ItemVO create(ItemVO item) throws GeneralException {
//        ItemVO itemVO = super.create(item, item.getId());
//        return itemVO;
//    }

    @Override
    public ItemVO delete(Long id) {
        return null;
    }

    @Override
    public InputStream exportJson() throws IOException, GeneralException {

        File tempFile = File.createTempFile("json-report", ".json");

        List<Item> lst = itemRepository.findAll();
        List<ItemVO> lstVO = this.map2VO(lst, ItemVO.class);
        objectMapper.writeValue(tempFile, lstVO);

        return new FileInputStream(tempFile);
    }

    @Override
    @Transactional
    public Long importJson(InputStream file) throws IOException {
        List<ItemVO> items = objectMapper.readValue(file, new TypeReference<List<ItemVO>>() {
        });

        List<Item> lst = this.map2VO(items, Item.class);
        lst.forEach(o -> o.setId(null));
        List<Item> lstSaved = itemRepository.saveAll(lst);

//        Long newId = 1L;
//        for (ItemVO item : items) {
//            log.info("green " + item);
//            Item entity = modelMapper.map(item, Item.class);
//            Item save = itemRepository.save(entity);
//        }

        return Long.valueOf(lstSaved.size());
    }
}
