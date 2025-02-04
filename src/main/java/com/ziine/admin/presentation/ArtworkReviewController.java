package com.ziine.admin.presentation;

import com.ziine.admin.application.ArtworkReviewService;
import com.ziine.admin.application.dto.request.ArtworkRejectRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin/v1/artworks")
@RequiredArgsConstructor
public class ArtworkReviewController {

    private final ArtworkReviewService artworkReviewService;

    @PatchMapping("/{artworkId}/approve")
    public ResponseEntity<Void> approveArtwork(final @PathVariable Long artworkId) {
        artworkReviewService.approveArtwork(artworkId);
        return ResponseEntity.noContent()
            .build();
    }
}
