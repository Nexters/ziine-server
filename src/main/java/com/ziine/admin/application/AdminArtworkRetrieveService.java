package com.ziine.admin.application;

import com.ziine.admin.application.dto.response.AdminArtworkRetrieveResponseDto;
import com.ziine.artwork.domain.entity.ArtworkStatus;
import com.ziine.artwork.domain.repository.ArtworkRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AdminArtworkRetrieveService {

    private final ArtworkRepository artworkRepository;

    @Transactional(readOnly = true)
    public List<AdminArtworkRetrieveResponseDto> retrieveArtworksByStatus(final ArtworkStatus artworkStatus) {
        if (artworkStatus == null) {
            return artworkRepository.findAllByOrderByCreatedAtAsc()
                .stream()
                .map(AdminArtworkRetrieveResponseDto::fromEntity)
                .toList();
        }

        return artworkRepository.findByStatusOrderByCreatedAtAsc(artworkStatus)
            .stream()
            .map(AdminArtworkRetrieveResponseDto::fromEntity)
            .toList();
    }
}
