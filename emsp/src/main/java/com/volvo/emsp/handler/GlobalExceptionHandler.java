package com.volvo.emsp.handler;

import com.google.common.collect.Lists;
import com.volvo.application.excetpion.CommonException;
import com.volvo.representation.UnknownUserException;
import com.volvo.representation.response.CommonResponse;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Map;

import static com.volvo.application.model.ResponseType.*;
import static java.util.Objects.requireNonNull;
import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public CommonResponse<List<Map<String, String>>> handle(MethodArgumentNotValidException ex) {
        List<Map<String, String>> errors = Lists.newArrayList();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.add(Map.of(fieldName, requireNonNull(errorMessage)));
        });
        return CommonResponse.by(NOT_VALID_ARGUMENT, errors);
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public <T> CommonResponse<T> handleException(Exception exception) {
        log.error("unknown exception: ", exception);
        return CommonResponse.by(SERVE_ERROR);
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({CommonException.class})
    public <T> CommonResponse<T> handleException(CommonException e) {
        log.error("An exception occurred", e);
        return CommonResponse.by(e.getResponseType(), e.getExtraMsg());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler({UnknownUserException.class})
    public <T> CommonResponse<T> handleUnknownException(UnknownUserException e) {
        log.error("An exception occurred while authenticating the user.", e);
        return CommonResponse.by(e.getResponseType());
    }

    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler({FeignException.class})
    public CommonResponse<String> handleFeignException(FeignException e) {
        log.error("An exception occurred while RPC.", e);
        return CommonResponse.by(RPC_ERROR);
    }

    /**
     * 事务系统异常处理
     */
    @ExceptionHandler(TransactionSystemException.class)
    public <T> CommonResponse<T> handle(TransactionSystemException e) {
        log.error("Error while committing the transaction.", e);
        StringBuilder message = new StringBuilder();
        if (e.contains(ConstraintViolationException.class)) {
            // 字段校验异常
            Throwable cause = e.getRootCause();
            if (cause instanceof ConstraintViolationException) {
                ConstraintViolationException ex = (ConstraintViolationException) cause;
                for (ConstraintViolation<?> c : ex.getConstraintViolations()) {
                    message.append(c.getMessage());
                }
            }
        }

        return CommonResponse.<T>builder().code(VALIDATE_ERROR.getCode()).message(message.toString()).build();
    }

}
