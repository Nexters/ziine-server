package com.ziine.admin.presentation;

import com.ziine.admin.application.AdminArtworkRetrieveService;
import com.ziine.admin.application.dto.response.AdminArtworkRetrieveResponseDto;
import com.ziine.domains.artwork.domain.entity.ArtworkStatus;
import java.util.List;
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
    public ResponseEntity<List<AdminArtworkRetrieveResponseDto>> retrieveArtworks(
        @RequestParam(required = false) final ArtworkStatus artworkStatus
    ) {
        final List<AdminArtworkRetrieveResponseDto> adminArtworkRetrieveResponseDtos =
            adminArtworkRetrieveService.retrieveArtworksByStatus(artworkStatus);
        return ResponseEntity.ok(adminArtworkRetrieveResponseDtos);
    }
}
