package com.ziine.api.artwork.presentation;

import com.ziine.api.artwork.application.ArtworkFacade;
import com.ziine.api.artwork.dto.request.ArtworkPersistRequestDto;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/artworks")
@RestController
public class ArtworkPersistController {

    private final ArtworkFacade artworkFacade;

    @PostMapping()
    public ResponseEntity<Void> persistArtwork(
        @Valid @RequestBody final ArtworkPersistRequestDto artworkPersistRequestDto
    ) {
        final Long artworkId = artworkFacade.persistArtwork(artworkPersistRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/artworks/" + artworkId))
            .build();
    }
}
