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

    public static BackgroundColor calculateBackgroundColor(
        final int index,
        final int magazineSize
    ) {
        final boolean isShiftNeeded = ((magazineSize - 1) % BackgroundColor.values().length == 0);
        final int shift = (isShiftNeeded && index == magazineSize - 1) ? 1 : 0;

        return switch ((index + shift) % 5) {
            case 0 -> GREEN;
            case 1 -> ORANGE;
            case 2 -> PINK;
            case 3 -> SKYBLUE;
            default -> PURPLE;
        };
    }

}
