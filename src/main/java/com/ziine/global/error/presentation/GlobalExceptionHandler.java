package com.ziine.global.error.presentation;

import com.ziine.admin.auth.application.exception.AdminUnauthorizedException;
import com.ziine.global.error.application.exception.BusinessException;
import com.ziine.global.error.domain.ErrorCode;
import com.ziine.global.error.dto.ErrorResponseDto;
import jakarta.annotation.Nullable;
import jakarta.servlet.http.HttpServletRequest;
import java.util.stream.Collectors;
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
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        final MethodArgumentNotValidException methodArgumentNotValidException,
        final HttpHeaders httpHeaders,
        final HttpStatusCode httpStatusCode,
        final WebRequest webRequest
    ) {
        final String validationErrorMessage = methodArgumentNotValidException.getBindingResult()
            .getAllErrors()
            .stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.joining(", "));

        final ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(httpStatusCode, validationErrorMessage);

        return handleExceptionInternal(methodArgumentNotValidException, problemDetail, httpHeaders, httpStatusCode,
            webRequest);
    }

    @Override
    public ResponseEntity<Object> createResponseEntity(
        final @Nullable Object responseBody,
        final HttpHeaders httpHeaders,
        final HttpStatusCode httpStatusCode,
        final WebRequest webRequest
    ) {
        if (responseBody instanceof ProblemDetail problemDetail) {
            log.info("[CommonException] URI: {}, Status: {}, Message: {}",
                webRequest.getDescription(false), httpStatusCode, problemDetail.getDetail());

            return ResponseEntity.status(httpStatusCode)
                .body(new ErrorResponseDto(ErrorCode.BAD_REQUEST.getCode(), problemDetail.getDetail()));
        }
        log.error("[UnexpectedException] URI: {}, Message: {}", webRequest.getDescription(false), responseBody);

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .body(new ErrorResponseDto(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(), ErrorCode.INTERNAL_SERVER_ERROR.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleInternalException(
        final Exception exception,
        final HttpServletRequest httpServletRequest
    ) {
        log.error("[UnexpectedException] URI: {}, Message: {}",
            httpServletRequest.getRequestURI(), exception.getMessage());

        return ResponseEntity.status(ErrorCode.INTERNAL_SERVER_ERROR.getHttpStatus())
            .body(new ErrorResponseDto(ErrorCode.INTERNAL_SERVER_ERROR));
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto> handleBusinessException(
        final BusinessException businessException,
        final HttpServletRequest httpServletRequest
    ) {
        log.info("[BusinessException] URI: {}, Code: {}",
            httpServletRequest.getRequestURI(), businessException.getErrorCode());

        return ResponseEntity.status(businessException.getErrorCode()
                .getHttpStatus())
            .body(new ErrorResponseDto(businessException.getErrorCode()));
    }

    @ExceptionHandler(AdminUnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handleAdminUnauthorizedException(
        final AdminUnauthorizedException adminUnauthorizedException,
        final HttpServletRequest httpServletRequest
    ) {
        log.warn("[AdminUnauthorizedException] URI: {}, Code: {}",
            httpServletRequest.getRequestURI(), adminUnauthorizedException.getErrorCode()
                .getCode());

        return ResponseEntity.status(adminUnauthorizedException.getErrorCode()
                .getHttpStatus())
            .body(new ErrorResponseDto(adminUnauthorizedException.getErrorCode()));
    }
}
