package com.ziine.admin.artwork.application;

import com.ziine.admin.artwork.dto.request.AdminArtworkRejectRequestDto;
import com.ziine.admin.artwork.exception.AdminNotFoundException;
import com.ziine.admin.auth.application.AdminContextHolder;
import com.ziine.admin.auth.domain.Admin;
import com.ziine.api.artwork.application.ArtworkStatusMailService;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import com.ziine.api.artwork.domain.entity.ArtworkStatusHistoryEntity;
import com.ziine.api.artwork.domain.repository.ArtworkRepository;
import com.ziine.api.artwork.domain.repository.ArtworkStatusHistoryRepository;
import com.ziine.api.artwork.exception.ArtworkNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminArtworkReviewService {

    private final ArtworkRepository artworkRepository;
    private final ArtworkStatusHistoryRepository artworkStatusHistoryRepository;
    private final ArtworkStatusMailService artworkStatusMailService;

    @Transactional
    public void approveArtwork(final Long artworkId) {
        final ArtworkEntity artworkEntity = artworkRepository.findById(artworkId)
            .orElseThrow(() -> ArtworkNotFoundException.INSTANCE);
        final Admin admin = AdminContextHolder.getAdmin()
            .orElseThrow(() -> AdminNotFoundException.INSTANCE);

        final ArtworkStatus fromStatus = artworkEntity.getStatus();
        artworkEntity.updateStatus(ArtworkStatus.APPROVED);

        persistArtworkStatusChange(fromStatus, ArtworkStatus.APPROVED,
            null, admin.name(), artworkEntity);  // TODO. 추후 Spring Event 방식으로 변경하여 상태 변경을 저장하는 보조 관심사 분리
    }

    @Transactional
    public void rejectArtwork(
        final Long artworkId,
        final AdminArtworkRejectRequestDto adminArtworkRejectRequestDto
    ) {
        final ArtworkEntity artworkEntity = artworkRepository.findById(artworkId)
            .orElseThrow(() -> ArtworkNotFoundException.INSTANCE);
        final Admin admin = AdminContextHolder.getAdmin()
            .orElseThrow(() -> AdminNotFoundException.INSTANCE);

        final ArtworkStatus fromStatus = artworkEntity.getStatus();
        artworkEntity.updateStatus(ArtworkStatus.REJECTED);

        persistArtworkStatusChange(fromStatus, ArtworkStatus.REJECTED,
            adminArtworkRejectRequestDto.rejectionReason(), admin.name(), artworkEntity);
        // TODO. 추후 Spring Event 방식으로 변경하여 상태 변경을 저장하는 보조 관심사 분리
    }

    private void persistArtworkStatusChange(
        final ArtworkStatus fromStatus,
        final ArtworkStatus toStatus,
        final String reason,
        final String modifiedBy,
        final ArtworkEntity artworkEntity
    ) {
        final ArtworkStatusHistoryEntity artworkStatusHistoryEntity = artworkStatusHistoryRepository.save(
            new ArtworkStatusHistoryEntity(fromStatus, toStatus, reason, modifiedBy, artworkEntity));

        artworkStatusMailService.sendArtworkStatusChangeMail(artworkStatusHistoryEntity);
    }
}
