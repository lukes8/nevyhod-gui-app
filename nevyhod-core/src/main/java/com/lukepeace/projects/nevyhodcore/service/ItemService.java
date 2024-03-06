package com.lukepeace.projects.nevyhodcore.service;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.util.ItemFilter;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface ItemService extends IService<ItemVO, Long, ItemFilter> {

    List<ItemVO> findAll() throws GeneralException;

//    Page<ItemVO> findAll(ItemFilter filter);

    ItemVO findById(Long id);

//    ItemVO create(ItemVO item) throws GeneralException;

    void delete(Long id) throws GeneralException;

    InputStream exportJson() throws IOException, GeneralException;

    Long importJson(InputStream file) throws IOException;
}
