package com.ziine.domains.artist.domain.entity;

import com.ziine.domains.artist.dto.ArtistPersistDto;
import com.ziine.domains.artwork.domain.entity.ArtworkEntity;
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
    private final List<ArtworkEntity> artworkEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<EducationEntity> educationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExhibitionEntity> exhibitionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ContactEntity> contactEntities = new ArrayList<>();

    public ArtistEntity(
        final String name,
        final String profileImageUrl,
        final String email
    ) {
        this.name = name;
        this.profileImageUrl = profileImageUrl;
        this.email = email;
    }

    public static ArtistEntity fromArtistPersistDto(final ArtistPersistDto artistPersistDto) {
        ArtistEntity artistEntity = new ArtistEntity(artistPersistDto.artistName(),
            generateRandomProfileImageUrl(artistPersistDto.artistName()), artistPersistDto.email());

        artistPersistDto.educations()
            .forEach(education ->
                artistEntity.educationEntities.add(
                    new EducationEntity(education, artistEntity)));
        artistPersistDto.exhibitions()
            .forEach(exhibition ->
                artistEntity.exhibitionEntities.add(
                    ExhibitionEntity.fromExhibitionRequestDto(exhibition, artistEntity)));
        artistPersistDto.contacts()
            .forEach(contact ->
                artistEntity.contactEntities.add(
                    ContactEntity.fromContactRequestDto(contact, artistEntity)));
        return artistEntity;
    }

    private static String generateRandomProfileImageUrl(final String artistName) {
        // TODO: 랜덤 이미지 로직 추가 필요
        return "https://ziine.me/" + artistName + ".png";
    }
}
