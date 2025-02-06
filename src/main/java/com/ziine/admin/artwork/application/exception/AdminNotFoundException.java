package com.ziine.admin.artwork.application.exception;

import com.ziine.global.error.application.exception.BusinessException;
import com.ziine.global.error.domain.ErrorCode;

public class AdminNotFoundException extends BusinessException {

    public static final AdminNotFoundException INSTANCE = new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
