package com.lukepeace.projects.nevyhodcore.service.firebase;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;


public interface ItemService {

    List<ItemVO> findAll() throws GeneralException, ExecutionException, InterruptedException;

//    Page<ItemVO> findAll(ItemFilter filter);

    ItemVO findById(Long id) throws ExecutionException, InterruptedException, GeneralException;

    ItemVO create(ItemVO item) throws GeneralException, ExecutionException, InterruptedException;

    void delete(Long id) throws GeneralException, ExecutionException, InterruptedException;

    InputStream exportJson() throws IOException, GeneralException;

    Long importJson(InputStream file) throws IOException;

    void getAllItems(Consumer<List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO>> consumer) throws ExecutionException, InterruptedException;
    void insertItems() throws ExecutionException, InterruptedException;
}
