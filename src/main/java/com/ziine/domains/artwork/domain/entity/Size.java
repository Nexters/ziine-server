package com.ziine.domains.artwork.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class Size {

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private int width;

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private int height;

    public Size(
        final int width,
        final int height
    ) {
        this.width = width;
        this.height = height;
    }
}
