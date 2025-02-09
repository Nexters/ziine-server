package com.ziine.admin.artwork.application;

import com.ziine.admin.artwork.dto.response.AdminArtworksRetrieveResponseDto;
import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import com.ziine.api.artwork.domain.repository.ArtworkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminArtworkRetrieveService {

    private final ArtworkRepository artworkRepository;

    @Transactional(readOnly = true)
    public AdminArtworksRetrieveResponseDto retrieveArtworksByStatus(final ArtworkStatus artworkStatus) {
        if (artworkStatus == null) {  // TODO. 추후 Querydsl 도입 시 검색 조건을 동적으로 처리
            return AdminArtworksRetrieveResponseDto.fromEntities(artworkRepository.findAll());
        }

        return AdminArtworksRetrieveResponseDto.fromEntities(
            artworkRepository.findByStatusOrderByCreatedAtAsc(artworkStatus));
    }
}
