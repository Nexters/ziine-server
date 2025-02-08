package com.ziine.api.artist.dto.response;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.domain.entity.ContactEntity;
import com.ziine.api.artist.domain.entity.ContactType;
import com.ziine.api.artist.domain.entity.EducationEntity;
import com.ziine.api.artist.domain.entity.ExhibitionEntity;
import java.time.LocalDate;
import java.util.List;

public record ArtistDetailResponseDto(
    Long id,
    String name,
    String profileImageUrl,
    String email,
    List<String> educations,
    List<ExhibitionDto> exhibitions,
    List<ContactDto> contacts
) {

    public static ArtistDetailResponseDto fromEntity(final ArtistEntity artistEntity) {
        return new ArtistDetailResponseDto(
            artistEntity.getId(),
            artistEntity.getName(),
            artistEntity.getProfileImageUrl(),
            artistEntity.getEmail(),
            artistEntity.getEducationEntities()
                .stream()
                .map(EducationEntity::getTag)
                .toList(),
            artistEntity.getExhibitionEntities()
                .stream()
                .map(ExhibitionDto::fromEntity)
                .toList(),
            artistEntity.getContactEntities()
                .stream()
                .map(ContactDto::fromEntity)
                .toList()
        );
    }

    public record ExhibitionDto(
        String title,
        LocalDate exhibitionDate
    ) {

        public static ExhibitionDto fromEntity(final ExhibitionEntity exhibitionEntity) {
            return new ExhibitionDto(exhibitionEntity.getTitle(), exhibitionEntity.getExhibitionDate());
        }
    }

    public record ContactDto(
        ContactType type,
        String value
    ) {

        public static ContactDto fromEntity(final ContactEntity contactEntity) {
            return new ContactDto(contactEntity.getType(), contactEntity.getValue());
        }
    }

}
