package com.ziine.common.exception;

import com.ziine.common.dto.ErrorResponseDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> createResponseEntity(@Nullable Object body, HttpHeaders headers,
                                                          HttpStatusCode statusCode, WebRequest request) {
        if (body instanceof ProblemDetail problemDetail) {
            log.warn("ProblemDetail: {}", problemDetail);
            return ResponseEntity.status(statusCode)
                    .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), problemDetail.getDetail()));
        }
        log.error("Response body is not an instance of ProblemDetail. Body: {}", body);
        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
                .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    protected ResponseEntity<ErrorResponseDto> handleBusinessException(
        final BusinessException e,
        final HttpServletRequest request
    ) {
        log.error("BusinessException: {} Request Url: {}", e.getErrorCode(), request.getRequestURL());
        return ResponseEntity
            .status(e.getErrorCode().getHttpStatus().value())
            .body(new ErrorResponseDto(e.getErrorCode()));
    }

    @ExceptionHandler(value = {Exception.class, RuntimeException.class, SQLException.class,
        DataIntegrityViolationException.class})
    protected ResponseEntity<ErrorResponseDto> handleInternalException(
        final Exception e,
        final HttpServletRequest request
    ) {
        log.error("Request URL: {}", request.getRequestURL(), e);

        return ResponseEntity
            .status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus().value())
            .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
