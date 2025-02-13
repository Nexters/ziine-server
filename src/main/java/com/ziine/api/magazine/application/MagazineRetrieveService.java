package com.ziine.api.magazine.application;

import com.ziine.api.magazine.domain.entity.MagazineEntity;
import com.ziine.api.magazine.domain.repository.MagazineRepository;
import com.ziine.api.magazine.dto.response.MagazineDetailRetrieveResponseDto;
import com.ziine.api.magazine.dto.response.MagazinesRetrieveResponseDto;
import com.ziine.api.magazine.dto.response.MagazinesRetrieveResponseDto.MagazineRetrieveDto;
import com.ziine.api.magazine.exception.MagazineNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MagazineRetrieveService {

    private final MagazineRepository magazineRepository;

    @Transactional(readOnly = true)
    public MagazinesRetrieveResponseDto retrieveMagazines() {
        final List<MagazineEntity> magazineEntities = magazineRepository.findAllByOrderByCreatedAtDesc();
        return new MagazinesRetrieveResponseDto(
            magazineEntities.stream()
                .map(MagazineRetrieveDto::fromEntity)
                .toList(),
            magazineEntities.size()
        );
    }

    @Transactional(readOnly = true)
    public MagazineDetailRetrieveResponseDto retrieveMagazineDetail(final Long magazineId) {
        return MagazineDetailRetrieveResponseDto.fromEntity(
            magazineRepository.findById(magazineId)
                .orElseThrow(() -> MagazineNotFoundException.INSTANCE)
        );
    }
}
