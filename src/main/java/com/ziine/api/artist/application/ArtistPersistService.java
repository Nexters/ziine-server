package com.ziine.api.artist.application;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.domain.repository.ArtistRepository;
import com.ziine.api.artist.dto.ArtistPersistDto;
import com.ziine.global.AWSProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@EnableConfigurationProperties(AWSProperties.class)
@Service
public class ArtistPersistService {

    private final ArtistRepository artistRepository;

    public ArtistEntity persistArtist(final ArtistPersistDto artistPersistDto) {
        return artistRepository.save(
            ArtistEntity.fromArtistPersistDto(
                artistPersistDto
            )
        );
    }
}
