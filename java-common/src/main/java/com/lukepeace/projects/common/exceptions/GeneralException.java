package com.lukepeace.projects.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor @Getter
public class GeneralException extends Exception {

    private GeneralExceptionCodes exceptionCode;
    private String exceptionMessage;
}
