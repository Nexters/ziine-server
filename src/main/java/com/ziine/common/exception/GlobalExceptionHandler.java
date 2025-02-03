package com.ziine.common.exception;

import com.ziine.common.dto.ErrorResponseDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException e,
        final HttpHeaders headers,
        final HttpStatusCode statusCode,
        final WebRequest request
    ) {
        final String message = e.getBindingResult()
            .getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));

        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(statusCode, message);

        return handleExceptionInternal(e, problemDetail, headers, statusCode, request);
    }

    @Override
    public ResponseEntity<Object> createResponseEntity(
        final @Nullable Object body,
        final HttpHeaders headers,
        final HttpStatusCode statusCode,
        final WebRequest request
    ) {
        if (body instanceof ProblemDetail problemDetail) {
            log.info("[CommonException] URI: {}, Status: {}, Message: {}",
                request.getDescription(false), statusCode, problemDetail.getDetail());

            return ResponseEntity.status(statusCode)
                .body(new ErrorResponseDto(ErrorCode.BAD_REQUEST.getCode(), problemDetail.getDetail()));
        }
        log.error("[UnexpectedException] URI: {}, Message: {}", request.getDescription(false), body);

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .body(new ErrorResponseDto(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(
        final BusinessException e,
        final HttpServletRequest request
    ) {
        log.info("[BusinessException] URI: {}, Code: {}, Message: {}",
            request.getRequestURI(), e.getErrorCode()
                .getCode(), e.getMessage()
        );

        return ResponseEntity.status(e.getErrorCode()
                .getHttpStatus())
            .body(new ErrorResponseDto(e.getErrorCode()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleInternalException(
        final Exception e,
        final HttpServletRequest request
    ) {
        log.error("[UnexpectedException] URI: {}, Message: {}",
            request.getRequestURI(), e.getMessage());

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
