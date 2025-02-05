package com.ziine.domains.artist.domain.entity;

import com.ziine.domains.artist.domain.dto.ArtistPersistDto;
import com.ziine.domains.artwork.domain.entity.ArtworkEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ArtworkEntity> artworkEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<EducationEntity> educationEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ExhibitionEntity> exhibitionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "artist", cascade = CascadeType.ALL, orphanRemoval = true)
    private final List<ContactEntity> contactEntities = new ArrayList<>();

    public ArtistEntity(final ArtistPersistDto artistPersistDto) {
        this.name = artistPersistDto.artistName();
        // Todo: 랜덤 이미지 넣는 로직으로 수정 필요
        this.profileImageUrl = "https://ziine.me/" + name + ".png";
        this.email = artistPersistDto.email();
        artistPersistDto.educations().forEach(education -> {
            educationEntities.add(new EducationEntity(education, this));
        });
        artistPersistDto.exhibitions().forEach(exhibition -> {
            exhibitionEntities.add(new ExhibitionEntity(exhibition, this));
        });
        artistPersistDto.contacts().forEach(contact -> {
            contactEntities.add(new ContactEntity(contact, this));
        });
    }
}
