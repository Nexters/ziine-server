package com.ziine.api.artwork.domain.entity;

import lombok.Getter;

@Getter
public enum ArtworkStatus {
    
    REQUEST("요청"),
    PENDING("검토"),
    APPROVED("승인"),
    REJECTED("반려");

    private final String description;

    ArtworkStatus(final String description) {
        this.description = description;
    }
}
