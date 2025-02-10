package com.ziine.admin.magazine.presentation;

import com.ziine.admin.magazine.application.AdminMagazinePersistService;
import com.ziine.admin.magazine.dto.request.AdminMagazinePersistRequestDto;
import jakarta.validation.Valid;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/admin/v1/magazines")
@RestController
public class AdminMagazinePersistController {

    private final AdminMagazinePersistService adminMagazinePersistService;

    @PostMapping
    public ResponseEntity<Void> persistMagazine(
        @Valid @RequestBody final AdminMagazinePersistRequestDto adminMagazinePersistRequestDto
    ) {
        final Long magazineId = adminMagazinePersistService.persistMagazine(adminMagazinePersistRequestDto);
        return ResponseEntity.created(URI.create("/api/v1/magazines/" + magazineId))
            .build();
    }
}
