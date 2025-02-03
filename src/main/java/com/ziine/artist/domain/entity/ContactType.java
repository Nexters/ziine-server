package com.ziine.artist.domain.entity;

import lombok.Getter;

@Getter
public enum ContactType {

    INSTAGRAM("인스타그램"),
    WEBSITE("웹사이트"),
    OTHER("기타");

    private final String description;

    ContactType(final String description) {
        this.description = description;
    }
}
