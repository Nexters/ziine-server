package com.ziine.api.artist.dto;

import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto;
import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto.ContactRequestDto;
import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto.ExhibitionRequestDto;
import java.util.List;

public record ArtistPersistDto(
    String artistName,
    List<String> educations,
    List<ExhibitionRequestDto> exhibitions,
    List<ContactRequestDto> contacts,
    String email
) {

    public static ArtistPersistDto fromArtworkPersistRequestDto(
        final ArtworkPersistRequestDto artworkPersistRequestDto
    ) {
        return new ArtistPersistDto(
            artworkPersistRequestDto.artistName(),
            artworkPersistRequestDto.educations(),
            artworkPersistRequestDto.exhibitions(),
            artworkPersistRequestDto.contacts(),
            artworkPersistRequestDto.email()
        );
    }
}
