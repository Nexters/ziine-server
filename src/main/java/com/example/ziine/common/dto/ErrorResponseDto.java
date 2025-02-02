package com.example.ziine.common.dto;

import com.example.ziine.common.exception.ErrorCode;

public record ErrorResponseDto(int code, String message) {

    public ErrorResponseDto(final ErrorCode errorCode) {
        this(errorCode.getCode(), errorCode.getMessage());
    }
}