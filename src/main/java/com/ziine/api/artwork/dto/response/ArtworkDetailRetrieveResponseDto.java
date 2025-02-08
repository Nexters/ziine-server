package com.ziine.api.artwork.dto.response;

import com.ziine.api.artist.dto.response.ArtistDetailResponseDto;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;

public record ArtworkDetailRetrieveResponseDto(
    Long id,
    String title,
    Integer width,
    Integer height,
    String material,
    String description,
    String artworkImageUrl,
    String shareUrl,
    ArtistDetailResponseDto artist
) {

    public static ArtworkDetailRetrieveResponseDto fromEntity(ArtworkEntity artworkEntity) {
        return new ArtworkDetailRetrieveResponseDto(
            artworkEntity.getId(),
            artworkEntity.getTitle(),
            artworkEntity.getSizeAttribute()
                .getWidth(),
            artworkEntity.getSizeAttribute()
                .getHeight(),
            artworkEntity.getMaterial(),
            artworkEntity.getDescription(),
            artworkEntity.getImageUrl(),
            generateShareUrl(artworkEntity.getId()),
            ArtistDetailResponseDto.fromEntity(artworkEntity.getArtistEntity())
        );
    }

    private static String generateShareUrl(Long id) {
        return "https://www.ziine.gallery/artworks/" + id.toString();
    }
}
