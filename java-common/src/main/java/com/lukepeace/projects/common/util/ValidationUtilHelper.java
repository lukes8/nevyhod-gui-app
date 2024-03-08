package com.lukepeace.projects.common.util;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.GeneralExceptionCodes;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Component @Slf4j @Validated
public class ValidationUtilHelper<E> {
    @Autowired private MessageWrapper messageWrapper;
    @Autowired private ApplicationContext appContext;
    private final int PARAM_TYPE_IDX_REPO = 2;
    public GeneralException buildGeneralException(GeneralExceptionCodes code) {
        String msg = messageWrapper.getMessage(code.toString());
        GeneralException ex = new GeneralException(code, msg);
        log.debug(ex.getMessage());
        return ex;

    }

    public void validate(@Valid final E entity) {}

    public void validate(@Valid final List<E> lstEntities) {}

//    private R getRepository() {
//        Type t = ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[PARAM_TYPE_IDX_REPO];
//        R obj = (R) appContext.getBean((Class)t);
//        return obj;
//    }

//    public void validateBeforeCreate(ID id) throws GeneralException {
//        R repo = getRepository();
//        if (repo.existsById(id)) {
//            throw buildGeneralException(NevyhodExceptionCodes.ITEM_ALREADY_EXISTS);
//        }
//    }
}
