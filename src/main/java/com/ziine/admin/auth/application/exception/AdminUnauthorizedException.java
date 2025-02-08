package com.ziine.admin.auth.application.exception;

import com.ziine.common.exception.BusinessException;
import com.ziine.common.exception.ErrorCode;

public class AdminUnauthorizedException extends BusinessException {

    public static final AdminUnauthorizedException INSTANCE = new AdminUnauthorizedException();

    private AdminUnauthorizedException() {
        super(ErrorCode.ADMIN_UNAUTHORIZED);
    }
}
