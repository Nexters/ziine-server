package com.ziine.admin.magazine.application;

import com.ziine.admin.magazine.dto.request.AdminMagazinePersistRequestDto;
import com.ziine.api.magazine.domain.entity.MagazineEntity;
import com.ziine.api.magazine.domain.repository.MagazineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AdminMagazinePersistService {

    private final MagazineRepository magazineRepository;

    public Long persistMagazine(final AdminMagazinePersistRequestDto adminMagazinePersistRequestDto) {
        final MagazineEntity magazineEntity =
            MagazineEntity.fromAdminMagazinePersistRequestDto(adminMagazinePersistRequestDto);

        return magazineRepository.save(magazineEntity)
            .getId();
    }
}
