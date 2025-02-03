package com.ziine.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    /**
     * 1XXX -> Common 에러
     */
    BAD_REQUEST(
            1000,
            "Bad Request",
            HttpStatus.BAD_REQUEST
    ),
    INTERNAL_SERVER_ERROR(
            1001,
            "Internal Server Error",
            HttpStatus.INTERNAL_SERVER_ERROR
    ),

    /**
     * 2XXX -> Artwork 에러
     */
    ARTWORK_NOT_FOUND(
            2001,
            "Artwork Not Found",
            HttpStatus.BAD_REQUEST
    ),
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
