package com.ziine.api.artwork.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.api.artist.domain.entity.ContactType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import java.time.LocalDate;
import java.util.List;

public record ArtworkPersistRequestDto(
    @NotBlank(message = "Title is required")
    String title,
    @PositiveOrZero(message = "Width must be zero or greater")
    int width,
    @PositiveOrZero(message = "Height must be zero or greater")
    int height,
    @NotBlank(message = "Material is required")
    String material,
    @NotBlank(message = "Artwork image URL is required")
    String artworkImageUrl,
    @NotBlank(message = "Artist name is required")
    String artistName,
    String description,
    List<@NotBlank(message = "Education cannot be blank") String> educations,
    @Valid
    List<ExhibitionRequestDto> exhibitions,
    @Valid
    List<ContactRequestDto> contacts,
    String email
) {

    public record ExhibitionRequestDto(
        @NotBlank(message = "Exhibition title is required")
        String title,
        @NotNull(message = "Exhibition date is required")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate exhibitionDate
    ) {

    }

    public record ContactRequestDto(
        @NotNull(message = "Contact type is required")
        ContactType type,
        @NotBlank(message = "Contact value is required")
        String value
    ) {

    }
}
