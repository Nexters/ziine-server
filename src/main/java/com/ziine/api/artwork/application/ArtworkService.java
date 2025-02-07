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

    @Transactional
    public ArtworkEntity persistArtwork(
        final ArtworkPersistDto artworkPersistDto,
        final ArtistEntity artistEntity
    ) {
        final ArtworkEntity artworkEntity = artworkRepository.save(
            ArtworkEntity.fromArtworkPersistDto(artworkPersistDto, artistEntity));

        artworkStatusHistoryRepository.save(  // TODO. 추후 Spring Event 방식으로 변경하여 상태 변경을 저장하는 보조 관심사 분리
            new ArtworkStatusHistoryEntity(ArtworkStatus.REQUEST, ArtworkStatus.PENDING,
                null, artistEntity.getName(), artworkEntity)
        );

        return artworkEntity;
    }
}
