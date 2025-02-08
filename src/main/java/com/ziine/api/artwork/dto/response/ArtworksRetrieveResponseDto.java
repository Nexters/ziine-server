package com.ziine.api.artwork.dto.response;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import java.time.ZonedDateTime;
import java.util.List;

public record ArtworksRetrieveResponseDto(
    List<ArtworkRetrieveResponseDto> artworks
) {

    public record ArtworkRetrieveResponseDto(
        Long id,
        String title,
        String imageUrl,
        ZonedDateTime createdAt,
        ZonedDateTime modifiedAt,
        ArtworkRetrieveArtistDto artist
    ) {

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

        public static ArtworkRetrieveResponseDto fromEntity(final ArtworkEntity artworkEntity) {
            return new ArtworkRetrieveResponseDto(
                artworkEntity.getId(),
                artworkEntity.getTitle(),
                artworkEntity.getImageUrl(),
                artworkEntity.getCreatedAt(),
                artworkEntity.getModifiedAt(),
                ArtworkRetrieveArtistDto.fromEntity(artworkEntity.getArtistEntity())
            );
        }
    }
}
