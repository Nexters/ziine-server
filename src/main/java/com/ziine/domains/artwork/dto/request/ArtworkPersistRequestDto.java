package com.ziine.domains.artwork.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.domains.artist.domain.entity.ContactType;
import jakarta.validation.constraints.NotBlank;
import java.time.LocalDate;
import java.util.List;

public record ArtworkPersistRequestDto(
    @NotBlank
    String title,
    int width,
    int height,
    @NotBlank
    String material,
    @NotBlank
    String artworkImageUrl,
    @NotBlank
    String artistName,
    String description,
    List<String> educations,
    List<ExhibitionRequestDto> exhibitions,
    List<ContactRequestDto> contacts,
    String email
) {
    public record ExhibitionRequestDto(
        @NotBlank
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate exhibitionDate
    ) {}

    public record ContactRequestDto(
        ContactType type,
        String value
    ) {}
}

