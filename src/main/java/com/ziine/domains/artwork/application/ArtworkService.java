package com.ziine.domains.artwork.application;

import com.ziine.domains.artist.domain.entity.ArtistEntity;
import com.ziine.domains.artwork.domain.entity.ArtworkEntity;
import com.ziine.domains.artwork.domain.repository.ArtworkRepository;
import com.ziine.domains.artwork.dto.ArtworkPersistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class ArtworkService {
    private final ArtworkRepository artworkRepository;

    @Transactional
    public ArtworkEntity persistArtwork(final ArtworkPersistDto artworkPersistDto, final ArtistEntity artistEntity) {
        return artworkRepository.save(ArtworkEntity.fromArtworkPersistDto(artworkPersistDto, artistEntity));
    }
}
