package com.ziine.domains.artist.domain.application;

import com.ziine.domains.artist.domain.dto.ArtistPersistDto;
import com.ziine.domains.artist.domain.entity.ArtistEntity;
import com.ziine.domains.artist.domain.repository.ArtistRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ArtistService {
    private final ArtistRepository artistRepository;

    @Transactional
    public ArtistEntity persistArtist(final ArtistPersistDto artistPersistDto) {
        return artistRepository.save(new ArtistEntity(artistPersistDto));
    }
}
