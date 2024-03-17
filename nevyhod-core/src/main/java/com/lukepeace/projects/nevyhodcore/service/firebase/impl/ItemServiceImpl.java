package com.lukepeace.projects.nevyhodcore.service.firebase.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.NevyhodExceptionCodes;
import com.lukepeace.projects.nevyhodcore.service.firebase.ItemService;
import com.lukepeace.projects.nevyhodcore.util.MockDataHelper;
import com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;
import java.util.stream.Collectors;

@Service("itemReportService4Firebase") @Slf4j
public class ItemServiceImpl implements ItemService {

    @Autowired private ModelMapper modelMapper;

    @Override
    public List<ItemVO> findAll() throws GeneralException, ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        QuerySnapshot items = firestore.collectionGroup("items").get().get();
        List<ItemVO> results = items.getDocuments().stream()
                .map(d -> d.toObject(ItemVO.class))
                .collect(Collectors.toList());
        return results;
    }

    @Override
    public ItemVO findById(Long id) throws ExecutionException, InterruptedException, GeneralException {
        CollectionReference users = FirestoreClient.getFirestore().collection("items");
        DocumentReference doc = users.document("item-" + id.intValue());
        if (!doc.get().get().exists()) {
            throw new GeneralException(NevyhodExceptionCodes.ITEM_NOT_FOUND, "not found");
        }
        ItemVO itemVO = doc.get().get().toObject(ItemVO.class);
        return itemVO;
    }

    private String getNextIdValAsString() throws ExecutionException, InterruptedException {
        CollectionReference users = FirestoreClient.getFirestore().collection("items");
        QuerySnapshot queryDocumentSnapshots = users.get().get();
        return "item-" + queryDocumentSnapshots.size() + 1;
    }

    private int getNextIdVal() throws ExecutionException, InterruptedException {
        CollectionReference users = FirestoreClient.getFirestore().collection("items");
        QuerySnapshot queryDocumentSnapshots = users.get().get();
        return queryDocumentSnapshots.size() + 1;
    }

    @Override
    public ItemVO create(ItemVO item) throws GeneralException, ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        item.setId((long) getNextIdVal());
        ApiFuture<WriteResult> future = firestore.collection("items").document("item-" + item.getId()).set(item);
        WriteResult writeResult = future.get();
        log.info("Updated time: {}", writeResult.getUpdateTime());
        return item;
    }

    @Override
    public void delete(Long id) throws GeneralException {

    }

    @Override
    public InputStream exportJson() throws IOException, GeneralException {
        return null;
    }

    @Override
    public Long importJson(InputStream file) throws IOException {
        return null;
    }

    @Override
    public void getAllItems(Consumer<List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO>> consumer) {
        Firestore firestore = FirestoreClient.getFirestore();
        // arrow method did not return errors when mapping wrong data objects (hence direct getting above get())
        Query items2 = firestore.collectionGroup("items");
        items2.addSnapshotListener((value, error) -> {
            if (value != null) {
                List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO> results2 = value.getDocuments()
                        .stream()
                        .map(d -> d.toObject(com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO.class))
                        .collect(Collectors.toList());
                consumer.accept(results2);
            }
            if(error != null){
                log.error("failed", error);
            }
        });
    }

    @Override
    public void insertItems() throws ExecutionException, InterruptedException {
        Firestore firestore = FirestoreClient.getFirestore();
        List<ApiFuture<WriteResult>> futures = new ArrayList<>();
        CollectionReference items = firestore.collection("items");
        List<com.lukepeace.projects.nevyhodcore.vo.ItemVO> lst = MockDataHelper.dummyList4Item("luke");
        List<ItemVO> lstFinal = lst.stream().map(o -> modelMapper.map(o, ItemVO.class)).collect(Collectors.toList());
        lstFinal.forEach(o -> {
            futures.add(items.document("item-"+o.getId()).set(o));
        });
        List<WriteResult> writeResults = ApiFutures.allAsList(futures).get();
        writeResults.forEach(o -> log.info("Updated time: {}", o.getUpdateTime()));
    }
}
