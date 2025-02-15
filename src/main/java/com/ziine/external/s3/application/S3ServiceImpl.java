package com.ziine.external.s3.application;

import com.ziine.api.presigned.dto.PresignedUrl;
import com.ziine.global.AWSProperties;
import com.ziine.global.util.UrlUtil;
import java.time.Duration;
import java.util.Map;
import java.util.UUID;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

public class S3ServiceImpl implements S3Service {

    private static final String METADATA_KEY_ORIGINAL_NAME = "original-name";
    private static final Duration SIGNATURE_DURATION = Duration.ofMinutes(1);

    private final S3Presigner s3Presigner;
    private final AWSProperties awsProperties;

    public S3ServiceImpl(
        final S3Presigner s3Presigner,
        final AWSProperties awsProperties
    ) {
        this.s3Presigner = s3Presigner;
        this.awsProperties = awsProperties;
    }

    public PresignedUrl getPresignedUrl(
        final String fileName
    ) {
        final String newFileName = generateFileName(fileName);
        final String filePath = String.format("artwork/%s", newFileName);

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(awsProperties.getS3()
                .getBucket())
            .key(filePath)
            .metadata(Map.of(METADATA_KEY_ORIGINAL_NAME, fileName))
            .build();

        PutObjectPresignRequest preSignRequest = PutObjectPresignRequest.builder()
            .signatureDuration(SIGNATURE_DURATION)
            .putObjectRequest(putObjectRequest)
            .build();

        String presignedUrl = s3Presigner.presignPutObject(preSignRequest)
            .url()
            .toString();
        String fileUrl = UrlUtil.join(
            awsProperties.getCdn()
                .getUrl(),
            filePath
        );

        return new PresignedUrl(presignedUrl, fileUrl);
    }

    private String generateFileName(final String originalFileName) {
        boolean hasExtension = originalFileName.contains(".");
        String newFileKey = UUID.randomUUID()
            .toString();

        if (hasExtension) {
            return newFileKey + originalFileName.substring(originalFileName.lastIndexOf("."));
        }
        return newFileKey;
    }
}
