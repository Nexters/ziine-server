package com.ziine.domains.artist.domain.entity;

import com.ziine.domains.artist.domain.dto.ArtistPersistDto;
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

    @OneToMany(mappedBy = "artistEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<ArtworkEntity> artworkEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<EducationEntity> educationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<ExhibitionEntity> exhibitionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artistEntity", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private final List<ContactEntity> contactEntities = new ArrayList<>();

    public static ArtistEntity fromArtistPersistDto(final ArtistPersistDto artistPersistDto) {
        ArtistEntity artistEntity = new ArtistEntity();
        artistEntity.name = artistPersistDto.artistName();
        // Todo: 랜덤 이미지 넣는 로직으로 수정 필요
        artistEntity.profileImageUrl = "https://ziine.me/" + artistEntity.name + ".png";
        artistEntity.email = artistPersistDto.email();

        artistPersistDto.educations()
            .forEach(education -> {
                artistEntity.educationEntities.add(new EducationEntity(education, artistEntity));
            });
        artistPersistDto.exhibitions()
            .forEach(exhibition -> {
                artistEntity.exhibitionEntities.add(
                    ExhibitionEntity.fromExhibitionRequestDto(exhibition, artistEntity));
            });
        artistPersistDto.contacts()
            .forEach(contact -> {
                artistEntity.contactEntities.add(ContactEntity.fromContactRequestDto(contact, artistEntity));
            });

        return artistEntity;
    }
}
