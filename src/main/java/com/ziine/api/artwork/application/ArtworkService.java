package com.ziine.api.artwork.application;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.domain.repository.ArtistRepository;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import com.ziine.api.artwork.domain.repository.ArtworkRepository;
import com.ziine.api.artwork.dto.ArtworkPersistDto;
import com.ziine.api.artwork.dto.ArtworksRetrieveResponseDto;
import com.ziine.api.artwork.dto.ArtworksRetrieveResponseDto.ArtworkRetrieveResponseDto;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ArtworkService {

    private final ArtworkRepository artworkRepository;

    @Transactional
    public ArtworkEntity persistArtwork(
        final ArtworkPersistDto artworkPersistDto,
        final ArtistEntity artistEntity
    ) {
        return artworkRepository.save(ArtworkEntity.fromArtworkPersistDto(artworkPersistDto, artistEntity));
    }

    public ArtworksRetrieveResponseDto retrieveArtworks() {
        List<ArtworkEntity> artworkEntities = artworkRepository.findByStatusOrderByCreatedAtDesc(
            ArtworkStatus.APPROVED
        );

        return new ArtworksRetrieveResponseDto(artworkEntities.stream()
            .map(
                ArtworkRetrieveResponseDto::fromEntity)
            .toList());
    }
}
