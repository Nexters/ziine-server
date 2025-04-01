package com.ziine.api.artwork.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.api.artist.dto.response.ArtistDetailResponseDto;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import java.time.ZonedDateTime;

public record ArtworkDetailRetrieveResponseDto(
    Long id,
    String title,
    Integer width,
    Integer height,
    String material,
    String description,
    String artworkImageUrl,
    String shareUrl,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime createdAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime modifiedAt,
    ArtistDetailResponseDto artist
) {

    public static ArtworkDetailRetrieveResponseDto fromEntity(final ArtworkEntity artworkEntity) {
        return new ArtworkDetailRetrieveResponseDto(
            artworkEntity.getId(),
            artworkEntity.getTitle(),
            artworkEntity.getSizeAttribute()
                .getWidth(),
            artworkEntity.getSizeAttribute()
                .getHeight(),
            artworkEntity.getMaterial(),
            artworkEntity.getDescription(),
            artworkEntity.getArtworkImageUrl(),
            generateShareUrl(artworkEntity.getId()),
            artworkEntity.getCreatedAt(),
            artworkEntity.getModifiedAt(),
            com.ziine.api.artist.dto.response.ArtistDetailResponseDto.fromEntity(artworkEntity.getArtistEntity())
        );
    }

    private static String generateShareUrl(Long id) {
        return "https://www.ziine.gallery/artwork/" + id.toString();
    }
}
