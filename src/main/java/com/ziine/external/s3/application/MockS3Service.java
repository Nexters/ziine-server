package com.ziine.external.s3.application;

import com.ziine.api.presigned.dto.PresignedUrl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockS3Service implements S3Service {

    @Override
    public PresignedUrl getPresignedUrl(final String fileName) {
        log.info("[Mock Presigned Url] FileName: {}", fileName);
        return new PresignedUrl("", "");
    }
}
