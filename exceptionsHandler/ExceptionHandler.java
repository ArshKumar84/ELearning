package com.Project.ELearning.exceptionsHandler;

import com.Project.ELearning.dtos.ExceptionDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerMapping;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ExceptionDto> resourceNotFound(ResourceNotFoundException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ExceptionDto(
                new Date(), ex.getMessage(), HttpStatus.NOT_FOUND
//                , request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
        ));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidParametersException.class)
    public ResponseEntity<ExceptionDto> invalidParameters(InvalidParametersException ex, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ExceptionDto(
                new Date(), ex.getMessage(), HttpStatus.BAD_REQUEST
//                , request.getAttribute(HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE).toString()
        ));
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> MethodArgumentNotValid(MethodArgumentNotValidException ex, HttpServletRequest request)
    {

        Map<String,String> fieldInValidationMap = new HashMap<>();
        BindingResult result=ex.getBindingResult();

        result.getFieldErrors().forEach(error->fieldInValidationMap.put(
                error.getField(),
                error.getDefaultMessage()
        ));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(fieldInValidationMap);

    }

}
