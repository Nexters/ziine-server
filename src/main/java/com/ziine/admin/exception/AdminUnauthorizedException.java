package com.ziine.admin.exception;

import com.ziine.common.exception.BusinessException;
import com.ziine.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AdminUnauthorizedException extends BusinessException {

    public static final AdminUnauthorizedException INSTANCE = new AdminUnauthorizedException();

    private AdminUnauthorizedException() {
        super(ErrorCode.ADMIN_UNAUTHORIZED);
    }
}
