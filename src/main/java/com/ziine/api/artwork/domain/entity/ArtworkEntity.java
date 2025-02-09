package com.ziine.api.artwork.domain.entity;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artwork.dto.ArtworkPersistDto;
import com.ziine.global.jpa.domain.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "artwork")
public class ArtworkEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 255)
    private String artworkImageUrl;

    @Column(nullable = false, length = 50)
    private String material;

    @Embedded
    private SizeAttribute sizeAttribute;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ArtworkStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artist_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private ArtistEntity artistEntity;

    public ArtworkEntity(
        final String title,
        final String description,
        final String artworkImageUrl,
        final String material,
        final SizeAttribute sizeAttribute,
        final ArtistEntity artistEntity
    ) {
        this.title = title;
        this.description = description;
        this.artworkImageUrl = artworkImageUrl;
        this.material = material;
        this.sizeAttribute = sizeAttribute;
        this.artistEntity = artistEntity;
        this.status = ArtworkStatus.PENDING;
    }

    public static ArtworkEntity fromArtworkPersistDto(
        final ArtworkPersistDto artworkPersistDto,
        final ArtistEntity artistEntity
    ) {
        return new ArtworkEntity(artworkPersistDto.title(), artworkPersistDto.description(),
            artworkPersistDto.artworkImageUrl(), artworkPersistDto.material(),
            new SizeAttribute(artworkPersistDto.width(), artworkPersistDto.height()), artistEntity);
    }

    public void updateStatus(final ArtworkStatus newStatus) {
        this.status = newStatus;
    }
}
