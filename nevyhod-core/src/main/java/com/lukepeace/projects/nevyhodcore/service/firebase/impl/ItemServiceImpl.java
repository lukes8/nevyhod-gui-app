package com.lukepeace.projects.nevyhodcore.service.firebase.impl;

import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutures;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.Query;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.nevyhodcore.service.firebase.ItemService;
import com.lukepeace.projects.nevyhodcore.util.MockDataHelper;
import com.lukepeace.projects.nevyhodcore.vo.ItemVO;
import lombok.extern.slf4j.Slf4j;
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
    @Override
    public List<ItemVO> findAll() throws GeneralException {
        return null;
    }

    @Override
    public ItemVO findById(Long id) {
        return null;
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
//        QuerySnapshot items = firestore.collectionGroup("items").get().get();
//        List<ItemVO> results = items.getDocuments().stream()
//                .map(d -> d.toObject(ItemVO.class))
//                .collect(Collectors.toList());
//        consumer.accept(results);

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
        List<com.lukepeace.projects.nevyhodcore.vo.firebase.ItemVO> lst = MockDataHelper.dummyList4Item4Firebase("luke");
        lst.forEach(o -> {
            futures.add(items.document("item-"+o.getId()).set(o));
        });
        List<WriteResult> writeResults = ApiFutures.allAsList(futures).get();
        writeResults.forEach(o -> log.info("Updated time: {}", o.getUpdateTime()));
    }
}
