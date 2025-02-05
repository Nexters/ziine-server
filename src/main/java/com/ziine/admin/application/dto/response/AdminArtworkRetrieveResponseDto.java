package com.ziine.admin.application.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.domains.artist.domain.entity.ArtistEntity;
import com.ziine.domains.artwork.domain.entity.ArtworkEntity;
import com.ziine.domains.artwork.domain.entity.ArtworkStatus;
import java.time.ZonedDateTime;

public record AdminArtworkRetrieveResponseDto(
    Long artworkId,
    String title,
    String description,
    String imageUrl,
    String material,
    int width,
    int height,
    ArtworkStatus artworkStatus,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime createdAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime modifiedAt,
    AdminArtworkRetrieveArtistResponseDto artist
) {

    public static AdminArtworkRetrieveResponseDto fromEntity(final ArtworkEntity artworkEntity) {
        return new AdminArtworkRetrieveResponseDto(
            artworkEntity.getId(),
            artworkEntity.getTitle(),
            artworkEntity.getDescription(),
            artworkEntity.getImageUrl(),
            artworkEntity.getMaterial(),
            artworkEntity.getSizeAttribute()
                .getWidth(),
            artworkEntity.getSizeAttribute()
                .getHeight(),
            artworkEntity.getStatus(),
            artworkEntity.getCreatedAt(),
            artworkEntity.getModifiedAt(),
            AdminArtworkRetrieveArtistResponseDto.fromEntity(artworkEntity.getArtistEntity())
        );
    }

    public record AdminArtworkRetrieveArtistResponseDto(
        Long artistId,
        String name
    ) {

        public static AdminArtworkRetrieveArtistResponseDto fromEntity(final ArtistEntity artistEntity) {
            return new AdminArtworkRetrieveArtistResponseDto(
                artistEntity.getId(),
                artistEntity.getName()
            );
        }
    }
}
