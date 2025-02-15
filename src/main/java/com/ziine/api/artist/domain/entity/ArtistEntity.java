package com.ziine.api.artist.domain.entity;

import com.ziine.api.artist.dto.ArtistPersistDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "artist")
public class ArtistEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artist_id")
    private Long id;

    @Column(nullable = false, length = 80)
    private String name;

    @Column(nullable = false, length = 255)
    private String profileImageUrl;

    @Column(length = 255)
    private String email;

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<EducationEntity> educationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExhibitionEntity> exhibitionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ContactEntity> contactEntities = new ArrayList<>();

    public ArtistEntity(
        final String name,
        final String email,
        final String cdnUrl
    ) {
        this.name = name;
        this.profileImageUrl = generateRandomProfileImageUrl(cdnUrl);
        this.email = email;
    }

    private String generateRandomProfileImageUrl(final String cdnUrl) {
        int randomIndex = (int) (Math.random() * 3) + 1;
        return cdnUrl + "artist/profile_images/profile_image_" + randomIndex + ".png";
    }

    public void addEducations(final List<EducationEntity> educationEntities) {
        educationEntities.forEach(this::addEducation);
    }

    public void addEducation(final EducationEntity educationEntity) {
        this.educationEntities.add(educationEntity);
    }

    public void addExhibitions(final List<ExhibitionEntity> exhibitionEntities) {
        exhibitionEntities.forEach(this::addExhibition);
    }

    public void addExhibition(final ExhibitionEntity exhibitionEntity) {
        this.exhibitionEntities.add(exhibitionEntity);
    }

    public void addContacts(final List<ContactEntity> contactEntities) {
        contactEntities.forEach(this::addContact);
    }

    public void addContact(final ContactEntity contactEntity) {
        this.contactEntities.add(contactEntity);
    }

    public static ArtistEntity fromArtistPersistDto(
        final ArtistPersistDto artistPersistDto,
        String cdnUrl
    ) {
        final ArtistEntity artistEntity = new ArtistEntity(artistPersistDto.artistName(), artistPersistDto.email(),
            cdnUrl);

        if (artistPersistDto.educations() != null) {
            final List<EducationEntity> educationEntities = artistPersistDto.educations()
                .stream()
                .map(education -> new EducationEntity(education, artistEntity))
                .toList();
            artistEntity.addEducations(educationEntities);
        }

        if (artistPersistDto.exhibitions() != null) {
            final List<ExhibitionEntity> exhibitionEntities = artistPersistDto.exhibitions()
                .stream()
                .map(exhibition -> ExhibitionEntity.fromExhibitionRequestDto(exhibition, artistEntity))
                .toList();
            artistEntity.addExhibitions(exhibitionEntities);
        }

        if (artistPersistDto.contacts() != null) {
            final List<ContactEntity> contactEntities = artistPersistDto.contacts()
                .stream()
                .map(contact -> ContactEntity.fromContactRequestDto(contact, artistEntity))
                .toList();
            artistEntity.addContacts(contactEntities);
        }

        return artistEntity;
    }
}
