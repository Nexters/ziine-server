package com.ziine.api.artwork.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.ConstraintMode;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.time.ZonedDateTime;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "artwork_status_history")
public class ArtworkStatusHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "artwork_status_history_id")
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

    // ArtworkEntity에서 ArtworkStatusHistoryEntity를 조회할 일이 없으므로 양방향 관계로 설정하지 않음
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artwork_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), nullable = false)
    private ArtworkEntity artworkEntity;

    public ArtworkStatusHistoryEntity(
        final ArtworkStatus fromStatus,
        final ArtworkStatus toStatus,
        final String rejectionReason,
        final String changedBy,
        final ArtworkEntity artworkEntity
    ) {
        this.fromStatus = fromStatus;
        this.toStatus = toStatus;
        this.rejectionReason = rejectionReason;
        this.changedBy = changedBy;
        this.artworkEntity = artworkEntity;
    }

    @PrePersist
    public void prePersist() {
        this.changedAt = ZonedDateTime.now();
    }
}
