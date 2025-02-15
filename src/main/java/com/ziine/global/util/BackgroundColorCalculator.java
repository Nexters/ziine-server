package com.ziine.global.util;

import com.ziine.api.magazine.domain.BackgroundColor;

public class BackgroundColorCalculator {

    public static String getBackgroundColor(int index) {
        return switch (index % 3) {
            case 0 -> BackgroundColor.GREEN.getHexColorCode();
            case 1 -> BackgroundColor.ORANGE.getHexColorCode();
            default -> BackgroundColor.PURPLE.getHexColorCode();
        };
    }
}
