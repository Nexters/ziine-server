package com.ziine.api.artwork.exception;

import com.ziine.global.error.domain.ErrorCode;
import com.ziine.global.error.exception.BusinessException;

public class ArtworkNotFoundException extends BusinessException {

    public static final ArtworkNotFoundException INSTANCE = new ArtworkNotFoundException();

    private ArtworkNotFoundException() {
        super(ErrorCode.ARTWORK_NOT_FOUND);
    }
}
