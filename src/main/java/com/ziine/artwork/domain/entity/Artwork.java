package com.ziine.artwork.domain.entity;

import com.ziine.common.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "artwork")
public class Artwork extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_id")
    private Long id;

    @Column(nullable = false, length = 80)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, length = 255)
    private String imageUrl;

    @Column(nullable = false, length = 50)
    private String material;

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private int sizeWidth;

    @Column(nullable = false, columnDefinition = "SMALLINT UNSIGNED")
    private int sizeHeight;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ArtworkStatus status;

    @Column(nullable = false)
    private Long artistId;

    public Artwork(
            final String title,
            final String description,
            final String imageUrl,
            final String material,
            final int sizeWidth,
            final int sizeHeight,
            final Long artistId
    ) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.material = material;
        this.sizeWidth = sizeWidth;
        this.sizeHeight = sizeHeight;
        this.artistId = artistId;
        this.status = ArtworkStatus.PENDING;
    }

    public void updateStatus(final ArtworkStatus newStatus) {
        this.status = newStatus;
    }
}
