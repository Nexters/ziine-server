package com.ziine.external.s3.application;

import com.ziine.api.presigned.dto.PresignedUrl;

public interface S3Service {

    PresignedUrl getPresignedUrl(final String fileName);
}
