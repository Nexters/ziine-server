package com.ziine.api.magazine.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BackgroundColor {

    ORANGE("#FF571E"),
    PINK("#FF7777"),
    SKYBLUE("#84CEFF"),
    PURPLE("#6A99FF"),
    GREEN("#18C07A");

    private final String hexColorCode;

    public static BackgroundColor calculateBackgroundColor(int index) {
        return switch (index % 3) {
            case 0 -> GREEN;
            case 1 -> ORANGE;
            default -> PURPLE;
        };
    }

}
