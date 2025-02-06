package com.ziine.api.artwork.application.exception;

import com.ziine.global.error.application.exception.BusinessException;
import com.ziine.global.error.domain.ErrorCode;

public class ArtworkNotFoundException extends BusinessException {

    public static final ArtworkNotFoundException INSTANCE = new ArtworkNotFoundException();

    private ArtworkNotFoundException() {
        super(ErrorCode.ARTWORK_NOT_FOUND);
    }
}
