package com.lukepeace.projects.common.web.controller.rest;

import com.lukepeace.projects.common.exceptions.ClientErrorMessage;
import com.lukepeace.projects.common.exceptions.GeneralException;
import com.lukepeace.projects.common.exceptions.NevyhodExceptionCodes;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice @Slf4j
public class GeneralRestControllerBase extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<ClientErrorMessage> handleException(Exception e, HttpServletRequest request){

        String code = "unknown_code", msg = "unknown_message";
        if (e instanceof GeneralException) {
            code = ((GeneralException) e).getExceptionCode().toString();
            msg = ((GeneralException) e).getExceptionMessage();
        }
        else if (e instanceof BadCredentialsException) {
            code = NevyhodExceptionCodes.BAD_CREDENTIALS.toString();
            msg = e.getMessage();
        }
        else if (e instanceof ConstraintViolationException) {
            code = NevyhodExceptionCodes.CONSTRAINT_VIOLATION.toString();
            msg = e.getMessage();
        }
        else if (e instanceof InvalidDataAccessApiUsageException) {
            code = NevyhodExceptionCodes.INVALID_DATA_ACCESS_API_USAGE.toString();
            msg = e.getMessage();
        }

        if (e != null) {
            e.printStackTrace();
        }
        ClientErrorMessage m = new ClientErrorMessage(msg, code, request.getRequestURL().toString());
        return new ResponseEntity(m, HttpStatus.BAD_REQUEST);
    }
}
