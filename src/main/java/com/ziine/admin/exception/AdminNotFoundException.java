package com.ziine.admin.exception;

import com.ziine.common.exception.BusinessException;
import com.ziine.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class AdminNotFoundException extends BusinessException {

    public static final AdminNotFoundException INSTANCE = new AdminNotFoundException();

    private AdminNotFoundException() {
        super(ErrorCode.ADMIN_NOT_FOUND);
    }
}
