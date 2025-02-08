package com.ziine.global.error.dto;

import com.ziine.global.error.domain.ErrorCode;

public record ErrorResponseDto(int code, String message) {

    public ErrorResponseDto(final ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
}
