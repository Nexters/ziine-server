package com.ziine.admin.auth.exception;

import com.ziine.global.error.domain.ErrorCode;
import com.ziine.global.error.exception.BusinessException;

public class AdminUnauthorizedException extends BusinessException {

    public static final AdminUnauthorizedException INSTANCE = new AdminUnauthorizedException();

    private AdminUnauthorizedException() {
        super(ErrorCode.ADMIN_UNAUTHORIZED);
    }
}
