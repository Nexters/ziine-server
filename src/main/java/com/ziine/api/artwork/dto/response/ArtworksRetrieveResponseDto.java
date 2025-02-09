package com.ziine.api.artwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import java.time.ZonedDateTime;
import java.util.List;

public record ArtworksRetrieveResponseDto(
    List<ArtworkRetrieveResponseDto> artworks
) {

    public static ArtworksRetrieveResponseDto fromEntities(final List<ArtworkEntity> artworkEntities) {
        return new ArtworksRetrieveResponseDto(
            artworkEntities.stream()
                .map(ArtworkRetrieveResponseDto::fromEntity)
                .toList()
        );
    }

    public record ArtworkRetrieveResponseDto(
        Long id,
        String title,
        String artworkImageUrl,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        ZonedDateTime createdAt,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
        ZonedDateTime modifiedAt,
        ArtworkRetrieveArtistDto artist
    ) {

        public static ArtworkRetrieveResponseDto fromEntity(final ArtworkEntity artworkEntity) {
            return new ArtworkRetrieveResponseDto(
                artworkEntity.getId(),
                artworkEntity.getTitle(),
                artworkEntity.getArtworkImageUrl(),
                artworkEntity.getCreatedAt(),
                artworkEntity.getModifiedAt(),
                ArtworkRetrieveArtistDto.fromEntity(artworkEntity.getArtistEntity())
            );
        }

        public record ArtworkRetrieveArtistDto(
            Long id,
            String name,
            String profileImageUrl
        ) {

            public static ArtworkRetrieveArtistDto fromEntity(final ArtistEntity artistEntity) {
                return new ArtworkRetrieveArtistDto(
                    artistEntity.getId(),
                    artistEntity.getName(),
                    artistEntity.getProfileImageUrl()
                );
            }
        }
    }
}
