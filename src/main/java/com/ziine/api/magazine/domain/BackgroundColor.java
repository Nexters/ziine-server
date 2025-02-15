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
}
