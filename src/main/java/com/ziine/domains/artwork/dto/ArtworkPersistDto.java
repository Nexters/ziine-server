package com.ziine.domains.artwork.dto;

import com.ziine.domains.artwork.dto.request.ArtworkPersistRequestDto;

public record ArtworkPersistDto(
    String title,
    int width,
    int height,
    String material,
    String artworkImageUrl,
    String artistName,
    String description
) {
    public static ArtworkPersistDto fromArtworkPersistRequestDto(
        final ArtworkPersistRequestDto artworkPersistRequestDto
    ) {
        return new ArtworkPersistDto(
            artworkPersistRequestDto.title(),
            artworkPersistRequestDto.width(),
            artworkPersistRequestDto.height(),
            artworkPersistRequestDto.material(),
            artworkPersistRequestDto.artworkImageUrl(),
            artworkPersistRequestDto.artistName(),
            artworkPersistRequestDto.description()
        );
    }
}

