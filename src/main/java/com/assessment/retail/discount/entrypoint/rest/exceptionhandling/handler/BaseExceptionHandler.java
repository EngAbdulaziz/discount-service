package com.assessment.retail.discount.entrypoint.rest.exceptionhandling.handler;

import com.assessment.retail.discount.entrypoint.rest.exceptionhandling.model.BaseErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.codec.DecodingException;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebInputException;

import javax.validation.ConstraintViolationException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Optional;

import static com.assessment.retail.discount.entrypoint.rest.exceptionhandling.constant.ExceptionHandlerMessage.*;


@Slf4j
@RestControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<BaseErrorResponse> handleDataAccessException(
            DataAccessException ex, ServerWebExchange serverWebExchange) {

        log.error(DATA_ACCESS_EX_CAUGHT, ex.getMessage());

        BaseErrorResponse errorResponse =
                BaseErrorResponse.builder()
                        .message(ex.getMostSpecificCause().getMessage())
                        .description(ex.getMessage())
                        .build();

        boolean constraintViolated =
                Optional.ofNullable(ex.getMessage())
                        .map(err -> err.contains(ConstraintViolationException.class.getCanonicalName()))
                        .orElse(false);

        HttpStatus httpStatus;

        if (constraintViolated) {
            httpStatus = HttpStatus.CONFLICT;
        } else {
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    @ExceptionHandler(DecodingException.class)
    public ResponseEntity<BaseErrorResponse> handleDecodingException(
            DecodingException ex, ServerWebExchange serverWebExchange) {

        log.error(DECODING_EX_CAUGHT, ex.getMessage());

        BaseErrorResponse errorResponse =
                BaseErrorResponse.builder().message(ex.getMessage()).description(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler(javax.validation.ConstraintViolationException.class)
    public ResponseEntity<BaseErrorResponse> handleJavaXConstraintViolationException(
            javax.validation.ConstraintViolationException ex, ServerWebExchange serverWebExchange) {

        log.error(JAVAX_CONSTRAINT_VIOLATION_EX_CAUGHT, ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        BaseErrorResponse.builder()
                                .message(ex.getMessage())
                                .description(ex.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<BaseErrorResponse> handleServerWebInputException(
            ServerWebInputException ex) {

        log.error(SERVER_WEB_INPUT_EX_CAUGHT, ex.getMessage());

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(
                        BaseErrorResponse.builder()
                                .message(ex.getMessage())
                                .description(ex.getMessage())
                                .build());
    }

    @ExceptionHandler
    public ResponseEntity<BaseErrorResponse> handleWebExchangeBindException(
            WebExchangeBindException ex) {

        log.error(WEB_EXCHANGE_BIND_EX_CAUGHT, ex.getMessage());

        StringBuilder value = new StringBuilder();

        ex.getBindingResult()
                .getAllErrors()
                .forEach(
                        error -> {
                            value.append(error.getDefaultMessage());
                            value.append(" and ");
                        });

        value.replace(value.length() - 4, value.length() - 1, "");

        BaseErrorResponse errorResponse =
                BaseErrorResponse.builder().message(value.toString()).description(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errorResponse);
    }

    @ExceptionHandler
    public ResponseEntity<BaseErrorResponse> catchAll(Throwable ex) {

        StringWriter sw = new StringWriter();
        ex.printStackTrace(new PrintWriter(sw));
        String stacktrace = sw.toString();

        log.error(THROWABLE_CAUGHT, stacktrace);

        BaseErrorResponse errorResponse =
                BaseErrorResponse.builder().message(ex.getMessage()).description(ex.getMessage()).build();

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }
}
