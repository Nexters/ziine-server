package com.ziine.api.magazine.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ziine.api.magazine.domain.entity.KeywordEntity;
import com.ziine.api.magazine.domain.entity.MagazineEntity;
import java.time.ZonedDateTime;
import java.util.List;

public record MagazineDetailRetrieveResponseDto(
    Long id,
    String title,
    String summary,
    String thumbnailImageUrl,
    String content,
    List<String> keywords,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime createdAt,
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "UTC")
    ZonedDateTime modifiedAt
) {

    public static MagazineDetailRetrieveResponseDto fromEntity(MagazineEntity magazineEntity) {
        return new MagazineDetailRetrieveResponseDto(
            magazineEntity.getId(),
            magazineEntity.getTitle(),
            magazineEntity.getSummary(),
            magazineEntity.getThumbnailImageUrl(),
            magazineEntity.getContent(),
            magazineEntity.getKeywordEntities()
                .stream()
                .map(KeywordEntity::getTag)
                .toList(),
            magazineEntity.getCreatedAt(),
            magazineEntity.getModifiedAt()
        );
    }
}
