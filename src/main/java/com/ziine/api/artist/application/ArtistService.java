package com.ziine.api.artist.application;

import com.ziine.api.artist.domain.entity.ArtistEntity;
import com.ziine.api.artist.domain.repository.ArtistRepository;
import com.ziine.api.artist.dto.ArtistPersistDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ArtistService {

    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistEntity persistArtist(final ArtistPersistDto artistPersistDto) {
        return artistRepository.save(ArtistEntity.fromArtistPersistDto(artistPersistDto));
    }
}
