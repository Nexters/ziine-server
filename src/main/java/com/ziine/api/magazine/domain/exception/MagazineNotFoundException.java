package com.ziine.api.magazine.domain.exception;

import com.ziine.global.error.domain.ErrorCode;
import com.ziine.global.error.exception.BusinessException;

public class MagazineNotFoundException extends BusinessException {

    public static final MagazineNotFoundException INSTANCE = new MagazineNotFoundException();

    private MagazineNotFoundException() {
        super(ErrorCode.MAGAZINE_NOT_FOUND);
    }
}
