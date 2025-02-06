package com.ziine.admin.artwork.application;

import com.ziine.admin.artwork.application.dto.request.AdminArtworkRejectRequestDto;
import com.ziine.admin.artwork.application.exception.AdminNotFoundException;
import com.ziine.admin.artwork.domain.entity.ArtworkStatusHistoryEntity;
import com.ziine.admin.artwork.domain.repository.ArtworkStatusHistoryRepository;
import com.ziine.admin.auth.application.AdminContextHolder;
import com.ziine.admin.auth.domain.Admin;
import com.ziine.artwork.domain.entity.ArtworkEntity;
import com.ziine.artwork.domain.entity.ArtworkStatus;
import com.ziine.artwork.domain.repository.ArtworkRepository;
import com.ziine.common.exception.BusinessException;
import com.ziine.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminArtworkReviewService { // TODO. 추후 Spring Event 방식으로 변경하여 상태 변경을 저장하는 보조 관심사 분리

    private final ArtworkRepository artworkRepository;
    private final ArtworkStatusHistoryRepository artworkStatusHistoryRepository;

    @Transactional
    public void approveArtwork(final Long artworkId) {
        final ArtworkEntity artworkEntity = artworkRepository.findById(artworkId)
            .orElseThrow(
                () -> new BusinessException(ErrorCode.ARTWORK_NOT_FOUND)); // TODO. ArtworkNotFoundException 으로 변경
        final Admin admin = AdminContextHolder.getAdmin()
            .orElseThrow(() -> AdminNotFoundException.INSTANCE);

        final ArtworkStatus fromStatus = artworkEntity.getStatus();
        artworkEntity.updateStatus(ArtworkStatus.APPROVED);

        artworkStatusHistoryRepository.save(new ArtworkStatusHistoryEntity(
            fromStatus, ArtworkStatus.APPROVED, null, admin.name(), artworkEntity
        ));
    }

    @Transactional
    public void rejectArtwork(
        final Long artworkId,
        final AdminArtworkRejectRequestDto adminArtworkRejectRequestDto
    ) {
        final ArtworkEntity artworkEntity = artworkRepository.findById(artworkId)
            .orElseThrow(
                () -> new BusinessException(ErrorCode.ARTWORK_NOT_FOUND)); // TODO. ArtworkNotFoundException 으로 변경
        final Admin admin = AdminContextHolder.getAdmin()
            .orElseThrow(() -> AdminNotFoundException.INSTANCE);

        final ArtworkStatus fromStatus = artworkEntity.getStatus();
        artworkEntity.updateStatus(ArtworkStatus.REJECTED);

        artworkStatusHistoryRepository.save(new ArtworkStatusHistoryEntity(
            fromStatus, ArtworkStatus.REJECTED,
            adminArtworkRejectRequestDto.rejectionReason(), admin.name(), artworkEntity
        ));
    }
}
