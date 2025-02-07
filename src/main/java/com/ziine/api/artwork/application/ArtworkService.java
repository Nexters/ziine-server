package com.ziine.api.artwork.application;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import com.ziine.api.artwork.domain.entity.ArtworkStatusHistoryEntity;
import com.ziine.api.artwork.domain.repository.ArtworkRepository;
import com.ziine.api.artwork.domain.repository.ArtworkStatusHistoryRepository;
import com.ziine.api.artwork.dto.ArtworkPersistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArtworkService {

    private final ArtworkRepository artworkRepository;
    private final ArtworkStatusHistoryRepository artworkStatusHistoryRepository;
    private final ArtworkStatusMailService artworkStatusMailService;

    @Transactional
    public ArtworkEntity persistArtwork(
        final ArtworkPersistDto artworkPersistDto,
        final ArtistEntity artistEntity
    ) {
        final ArtworkEntity artworkEntity = artworkRepository.save(
            ArtworkEntity.fromArtworkPersistDto(artworkPersistDto, artistEntity));

        persistArtworkStatusChange(ArtworkStatus.REQUEST, ArtworkStatus.PENDING,
            null, artistEntity.getName(), artworkEntity);  // TODO. 추후 Spring Event 방식으로 변경하여 상태 변경을 저장하는 보조 관심사 분리

        return artworkEntity;
    }

    private void persistArtworkStatusChange(
        final ArtworkStatus fromStatus,
        final ArtworkStatus toStatus,
        final String reason,
        final String modifiedBy,
        final ArtworkEntity artworkEntity
    ) {
        final ArtworkStatusHistoryEntity artworkStatusHistoryEntity = artworkStatusHistoryRepository.save(
            new ArtworkStatusHistoryEntity(fromStatus, toStatus, reason, modifiedBy, artworkEntity)
        );

        artworkStatusMailService.sendArtworkStatusChangeMail(artworkStatusHistoryEntity);
    }
}
