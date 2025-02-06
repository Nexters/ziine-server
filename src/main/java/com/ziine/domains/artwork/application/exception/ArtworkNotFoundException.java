package com.ziine.domains.artwork.application.exception;

import com.ziine.common.exception.BusinessException;
import com.ziine.common.exception.ErrorCode;
import lombok.Getter;

@Getter
public class ArtworkNotFoundException extends BusinessException {

    public static final ArtworkNotFoundException INSTANCE = new ArtworkNotFoundException();

    private ArtworkNotFoundException() {
        super(ErrorCode.ARTWORK_NOT_FOUND);
    }
}
