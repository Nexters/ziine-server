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

        return switch (index % 5) {
            case 0 -> BackgroundColor.GREEN;
            case 1 -> BackgroundColor.ORANGE;
            case 2 -> BackgroundColor.PINK;
            case 3 -> BackgroundColor.SKYBLUE;
            default -> BackgroundColor.PURPLE;
        };
    }

}
