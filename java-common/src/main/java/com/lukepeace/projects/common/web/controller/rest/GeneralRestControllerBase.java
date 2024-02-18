package com.lukepeace.projects.common.web.controller.rest;

import com.lukepeace.projects.common.exceptions.ClientErrorMessage;
import com.lukepeace.projects.common.exceptions.GeneralException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GeneralRestControllerBase extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ClientErrorMessage> handleException(Exception e, HttpServletRequest request){

        String code = "unknown_code", msg = "unknown_message";
        if (e instanceof GeneralException) {
            code = ((GeneralException) e).getExceptionCode().toString();
            msg = ((GeneralException) e).getExceptionMessage();
        }
        ClientErrorMessage m = new ClientErrorMessage(msg, code, request.getRequestURL().toString());
        return new ResponseEntity(m, HttpStatus.BAD_REQUEST);
    }
}
