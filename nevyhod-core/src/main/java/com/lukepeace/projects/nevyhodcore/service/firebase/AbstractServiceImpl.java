package com.lukepeace.projects.nevyhodcore.service.firebase;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.NevyhodExceptionCodes;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutionException;

@Slf4j
public abstract class AbstractServiceImpl {

    private final Firestore firestore = FirestoreClient.getFirestore();

    protected void checkExistence(String collectionName, Long id) throws ExecutionException, InterruptedException, GeneralException {
        DocumentReference doc = firestore.collection(collectionName).document(""+id.intValue());
        if (!doc.get().get().exists()) {
            throw new GeneralException(NevyhodExceptionCodes.ITEM_NOT_FOUND, "not found");
        }
    }

    protected DocumentReference getDocument(String collectionName, Long id) throws ExecutionException, InterruptedException, GeneralException {
        DocumentReference doc = firestore.collection(collectionName).document("" + id.intValue());
        if (!doc.get().get().exists()) {
            throw new GeneralException(NevyhodExceptionCodes.ITEM_NOT_FOUND, "not found");
        }
        return doc;
    }

    protected WriteResult delete(String collectionName, Long id) throws GeneralException, ExecutionException, InterruptedException {
        checkExistence(collectionName, id);
        DocumentReference doc = getDocument(collectionName, id);
        ApiFuture<WriteResult> future = doc.delete();
        return future.get();
    }

}
