package com.ziine.domains.artwork.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.domains.artist.domain.entity.ContactType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

public record ArtworkPersistRequestDto(
    @NotBlank(message = "Title is required.")
    String title,
    @PositiveOrZero
    int width,
    @PositiveOrZero
    int height,
    @NotBlank(message = "Metarial is required.")
    String material,
    @NotBlank(message = "Artwork image url is required.")
    String artworkImageUrl,
    @NotBlank(message = "Artist name is required.")
    String artistName,
    String description,
    List<String> educations,
    List<ExhibitionRequestDto> exhibitions,
    List<ContactRequestDto> contacts,
    String email
) {

    public record ExhibitionRequestDto(
        @NotBlank(message = "Exhibition title is required.")
        String title,
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate exhibitionDate
    ) {

    }

    public record ContactRequestDto(
        @NotNull
        ContactType type,
        @NotBlank
        String value
    ) {

    }
}

