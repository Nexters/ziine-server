package com.ziine.api.presigned.presentation;

import com.ziine.api.presigned.application.PresignedService;
import com.ziine.api.presigned.dto.response.PresignedUrlResponseDto;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/v1/presigned-url")
@RestController
public class PresignedController {

    private final PresignedService presignedService;

    @GetMapping
    public ResponseEntity<PresignedUrlResponseDto> getPresignedUrl(
        @RequestParam final List<String> fileNames
    ) {
        return ResponseEntity.ok(presignedService.getPresignedUrl(fileNames));
    }
}
