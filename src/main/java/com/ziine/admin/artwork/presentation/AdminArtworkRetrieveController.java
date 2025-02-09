package com.ziine.admin.artwork.presentation;

import com.ziine.admin.artwork.application.AdminArtworkRetrieveService;
import com.ziine.admin.artwork.dto.response.AdminArtworksRetrieveResponseDto;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/v1/artworks")
@RestController
public class AdminArtworkRetrieveController {

    private final AdminArtworkRetrieveService adminArtworkRetrieveService;

    @GetMapping
    public ResponseEntity<AdminArtworksRetrieveResponseDto> retrieveArtworks(
        @RequestParam(required = false) final ArtworkStatus artworkStatus
    ) {
        final AdminArtworksRetrieveResponseDto adminArtworksRetrieveResponseDto =
            adminArtworkRetrieveService.retrieveArtworksByStatus(artworkStatus);
        return ResponseEntity.ok(adminArtworksRetrieveResponseDto);
    }
}
