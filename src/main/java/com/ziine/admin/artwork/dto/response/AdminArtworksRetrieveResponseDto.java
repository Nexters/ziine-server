package com.ziine.admin.artwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record AdminArtworksRetrieveResponseDto(
    List<AdminArtworkRetrieveResponseDto> artworks
) {

    public static AdminArtworksRetrieveResponseDto fromEntities(final List<ArtworkEntity> artworkEntities) {
        return new AdminArtworksRetrieveResponseDto(
            artworkEntities.stream()
                .map(AdminArtworkRetrieveResponseDto::fromEntity)
                .collect(Collectors.toList())
        );
    }

    public record AdminArtworkRetrieveResponseDto(
        Long artworkId,
        String title,
        String description,
        String artworkImageUrl,
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
                artworkEntity.getArtworkImageUrl(),
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
