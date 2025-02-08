package com.ziine.api.artwork.presentation;

import com.ziine.api.artwork.application.ArtworkFacade;
import com.ziine.api.artwork.dto.response.ArtworkDetailRetrieveResponseDto;
import com.ziine.api.artwork.dto.response.ArtworksRetrieveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/artworks")
@RestController
public class ArtworkRetrieveController {

    private final ArtworkFacade artworkFacade;

    @GetMapping()
    public ResponseEntity<ArtworksRetrieveResponseDto> retrieveArtworks() {
        return ResponseEntity.ok(artworkFacade.retrieveArtworks());
    }

    @GetMapping("/{artworkId}")
    public ResponseEntity<ArtworkDetailRetrieveResponseDto> retrieveArtworkDetail(
        @PathVariable("artworkId") final Long artworkId
    ) {
        return ResponseEntity.ok(artworkFacade.retrieveArtworkDetail(artworkId));
    }
}
