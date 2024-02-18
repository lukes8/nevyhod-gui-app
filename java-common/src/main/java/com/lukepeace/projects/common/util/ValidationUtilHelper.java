package com.lukepeace.projects.common.util;

import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.GeneralExceptionCodes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component @Slf4j
public class ValidationUtilHelper {

    @Autowired private MessageWrapper messageWrapper;

    public GeneralException buildGeneralException(GeneralExceptionCodes code) {
        String msg = messageWrapper.getMessage(code.toString());
        GeneralException ex = new GeneralException(code, msg);
        log.info(ex.getExceptionMessage());
        return ex;

    }


}
