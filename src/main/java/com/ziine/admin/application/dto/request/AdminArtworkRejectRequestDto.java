package com.ziine.admin.application.dto.request;

import jakarta.validation.constraints.NotBlank;

public record AdminArtworkRejectRequestDto(
    @NotBlank(message = "Rejection reason is required")
    String rejectionReason
) {

}
