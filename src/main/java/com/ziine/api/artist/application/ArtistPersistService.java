package com.ziine.api.artist.application;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.domain.repository.ArtistRepository;
import com.ziine.api.artist.dto.ArtistPersistDto;
import com.ziine.global.AWSProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@EnableConfigurationProperties(AWSProperties.class)
@Service
public class ArtistPersistService {

    private final ArtistRepository artistRepository;
    private final AWSProperties awsProperties;

    public ArtistPersistService(
        ArtistRepository artistRepository,
        @Qualifier("AWSProperties") AWSProperties awsProperties
    ) {
        this.artistRepository = artistRepository;
        this.awsProperties = awsProperties;
    }

    @Transactional
    public ArtistEntity persistArtist(final ArtistPersistDto artistPersistDto) {
        return artistRepository.save(
            ArtistEntity.fromArtistPersistDto(
                artistPersistDto,
                awsProperties.getCdn()
                    .getUrl()
            )
        );
    }

}
