package com.ziine.api.artwork.presentation;

import com.ziine.api.artwork.application.ArtworkFacade;
import com.ziine.api.artwork.dto.ArtworksRetrieveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/artworks")
@RequiredArgsConstructor
@RestController
public class ArtworkRetrieveController {

    private final ArtworkFacade artworkFacade;

    @GetMapping()
    public ResponseEntity<ArtworksRetrieveResponseDto> retrieveArtworks() {
        return ResponseEntity.ok(artworkFacade.retrieveArtworks());
    }
}
