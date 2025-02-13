package com.ziine.api.magazine.presentation;

import com.ziine.api.magazine.application.MagazineRetrieveService;
import com.ziine.api.magazine.dto.response.MagazineDetailRetrieveResponseDto;
import com.ziine.api.magazine.dto.response.MagazinesRetrieveResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/magazines")
@RestController
public class MagazineRetrieveController {

    private final MagazineRetrieveService magazineRetrieveService;

    @GetMapping("")
    public ResponseEntity<MagazinesRetrieveResponseDto> retrieveMagazines() {
        return ResponseEntity.ok(magazineRetrieveService.retrieveMagazines());
    }

    @GetMapping("/{magazineId}")
    public ResponseEntity<MagazineDetailRetrieveResponseDto> retrieveMagazineDetail(
        @PathVariable("magazineId") Long magazineId
    ) {
        return ResponseEntity.ok(magazineRetrieveService.retrieveMagazineDetail(magazineId));
    }

}
