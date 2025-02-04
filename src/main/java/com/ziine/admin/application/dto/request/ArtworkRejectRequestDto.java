package com.ziine.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record ArtworkRejectRequestDto(
    @NotBlank(message = "Rejection reason is required")
    String rejectionReason
) {

}
