package com.ziine.api.magazine.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BackgroundColor {
    GREEN("#1FC350"),
    ORANGE("#FF571E"),
    PURPLE("#9747FF");

    private final String hexColorCode;

    public static String calculateBackgroundColor(int index) {
        return switch (index % 3) {
            case 0 -> GREEN.getHexColorCode();
            case 1 -> ORANGE.getHexColorCode();
            default -> PURPLE.getHexColorCode();
        };
    }

}
