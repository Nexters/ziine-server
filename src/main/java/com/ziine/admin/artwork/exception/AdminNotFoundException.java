package com.ziine.admin.artwork.exception;

import com.ziine.global.error.domain.ErrorCode;
import com.ziine.global.error.exception.BusinessException;

public class AdminNotFoundException extends BusinessException {

    public static final AdminNotFoundException INSTANCE = new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
