package com.ziine.global.error.domain;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * 1XXX -> Common 에러
     */
    INTERNAL_SERVER_ERROR(1000, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR),
    BAD_REQUEST(1001, "Bad Request", HttpStatus.BAD_REQUEST),

    /**
     * 2XXX -> Artwork 에러
     */
    ARTWORK_NOT_FOUND(2001, "Artwork Not Found", HttpStatus.NOT_FOUND),

    /**
     * 3XXX -> Admin 에러
     */
    ADMIN_UNAUTHORIZED(3000, "Unauthorized Admin Access", HttpStatus.UNAUTHORIZED),
    ADMIN_NOT_FOUND(3001, "Admin Not Found", HttpStatus.NOT_FOUND),

    /**
     * 4XXX -> Magazine 에러
     */
    MAGAZINE_NOT_FOUND(4001, "Magazine Not Found", HttpStatus.NOT_FOUND),
    ;

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(
        final int code,
        final String message,
        final HttpStatus httpStatus
    ) {
        this.code = code;
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
