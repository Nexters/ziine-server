package com.ziine.api.artist.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ArtistProfileImage {

    PROFILE_IMAGE_1(1),
    PROFILE_IMAGE_2(2),
    PROFILE_IMAGE_3(3);
    private final int index;
    private final static String PROFILE_IMAGE_URL_PREFIX = "artist/profile_images/profile_image_";
    private final static String PROFILE_IMAGE_URL_POSTFIX = ".png";

    public static String generateRandomProfileImage(String cdnUrl) {
        int randomIndex = (int) (Math.random() * 3);
        ArtistProfileImage artistProfileImage;
        switch (randomIndex) {
            case 0 -> artistProfileImage = PROFILE_IMAGE_1;
            case 1 -> artistProfileImage = PROFILE_IMAGE_2;
            default -> artistProfileImage = PROFILE_IMAGE_3;
        }
        return cdnUrl + PROFILE_IMAGE_URL_PREFIX + artistProfileImage.getIndex() + PROFILE_IMAGE_URL_POSTFIX;
    }
}
