package com.ziine.api.presigned.application;

import com.ziine.api.presigned.dto.response.PresignedUrlResponseDto;
import com.ziine.external.s3.application.S3Service;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PresignedService {

    private final S3Service s3Service;

    public PresignedUrlResponseDto getPresignedUrl(final List<String> fileNames) {
        return new PresignedUrlResponseDto(fileNames.stream()
            .map(s3Service::getPresignedUrl)
            .toList());
    }
}
