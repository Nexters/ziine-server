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
        int colorIndex = (index + index / 5) % 5;

        return switch (colorIndex) {
            case 0 -> BackgroundColor.GREEN;
            case 1 -> BackgroundColor.ORANGE;
            case 2 -> BackgroundColor.PINK;
            case 3 -> BackgroundColor.SKYBLUE;
            default -> BackgroundColor.PURPLE;
        };
    }

}
