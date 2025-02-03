package com.ziine.artwork.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "artwork_status_history")
public class ArtworkStatusHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ArtworkStatus fromStatus;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ArtworkStatus toStatus;

    @Column(columnDefinition = "TEXT")
    private String rejectionReason;

    @Column(nullable = false, length = 20)
    private String changedBy;

    @Column(nullable = false, updatable = false)
    private ZonedDateTime changedAt;

    @Column(nullable = false)
    private Long artworkId;

    public ArtworkStatusHistoryEntity(
        final ArtworkStatus fromStatus,
        final ArtworkStatus toStatus,
        final String rejectionReason,
        final String changedBy,
        final Long artworkId
    ) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.rejectionReason = rejectionReason;
        this.changedBy = changedBy;
        this.artworkId = artworkId;
        this.changedAt = ZonedDateTime.now();
    }
}
