package com.ziine.admin.presentation;

import com.ziine.admin.application.AdminArtworkReviewService;
import com.ziine.admin.application.dto.request.AdminArtworkRejectRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/v1/artworks")
@RestController
public class AdminArtworkReviewController {

    private final AdminArtworkReviewService adminArtworkReviewService;

    @PatchMapping("/{artworkId}/approve")
    public ResponseEntity<Void> approveArtwork(final @PathVariable Long artworkId) {
        adminArtworkReviewService.approveArtwork(artworkId);
        return ResponseEntity.noContent()
            .build();
    }

    @PatchMapping("/{artworkId}/reject")
    public ResponseEntity<Void> rejectArtwork(
        final @PathVariable Long artworkId,
        final @Valid @RequestBody AdminArtworkRejectRequestDto adminArtworkRejectRequestDto
    ) {
        adminArtworkReviewService.rejectArtwork(artworkId, adminArtworkRejectRequestDto);
        return ResponseEntity.noContent()
            .build();
    }
}
