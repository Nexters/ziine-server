package com.ziine.admin.magazine.dto.request;

import jakarta.validation.constraints.NotBlank;
import java.util.List;

public record AdminMagazinePersistRequestDto(
    @NotBlank(message = "Title is required")
    String title,
    @NotBlank(message = "Summary is required")
    String summary,
    @NotBlank(message = "Thumbnail image URL is required")
    String thumbnailImageUrl,
    @NotBlank(message = "Content is required")
    String content,
    List<@NotBlank(message = "Keyword cannot be blank") String> keywords
) {

}
