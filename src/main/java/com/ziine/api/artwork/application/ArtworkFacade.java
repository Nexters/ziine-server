package com.ziine.api.artwork.application;

import com.ziine.api.artist.application.ArtistPersistService;
import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.dto.ArtistPersistDto;
import com.ziine.api.artwork.domain.entity.ArtworkEntity;
import com.ziine.api.artwork.dto.ArtworkPersistDto;
import com.ziine.api.artwork.dto.response.ArtworkDetailRetrieveResponseDto;
import com.ziine.api.artwork.dto.response.ArtworksRetrieveResponseDto;
import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto;
import com.ziine.global.AWSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@EnableConfigurationProperties(AWSProperties.class)
@Service
public class ArtworkFacade {

    private final ArtworkPersistService artworkPersistService;
    private final ArtworkRetrieveService artworkRetrieveService;
    private final ArtistPersistService artistPersistService;
    private final AWSProperties awsProperties;

    @Transactional
    public Long persistArtwork(final ArtworkPersistRequestDto artworkPersistRequestDto) {
        ArtistEntity artistEntity = artistPersistService.persistArtist(
            ArtistPersistDto.fromArtworkPersistRequestDto(
                artworkPersistRequestDto,
                awsProperties.getCdn()
                    .getUrl()
            )
        );
        ArtworkEntity artworkEntity = artworkPersistService.persistArtwork(
            ArtworkPersistDto.fromArtworkPersistRequestDto(artworkPersistRequestDto), artistEntity);

        return artworkEntity.getId();
    }

    @Transactional(readOnly = true)
    public ArtworksRetrieveResponseDto retrieveArtworks() {
        return artworkRetrieveService.retrieveArtworks();
    }

    @Transactional(readOnly = true)
    public ArtworkDetailRetrieveResponseDto retrieveArtworkDetail(final Long artworkId) {
        return artworkRetrieveService.retrieveArtworkDetail(artworkId);
    }
}
