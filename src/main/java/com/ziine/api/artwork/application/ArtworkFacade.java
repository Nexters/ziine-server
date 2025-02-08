package com.ziine.api.artwork.application;

import com.ziine.api.artist.application.ArtistService;
import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.dto.ArtistPersistDto;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.dto.ArtworkPersistDto;
import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArtworkFacade {

    private final ArtworkService artworkService;
    private final ArtistService artistService;

    @Transactional
    public Long persistArtwork(final ArtworkPersistRequestDto artworkPersistRequestDto) {
        ArtistEntity artistEntity = artistService.persistArtist(
            ArtistPersistDto.fromArtworkPersistRequestDto(artworkPersistRequestDto));
        ArtworkEntity artworkEntity = artworkService.persistArtwork(
            ArtworkPersistDto.fromArtworkPersistRequestDto(artworkPersistRequestDto), artistEntity);

        return artworkEntity.getId();
    }
}
