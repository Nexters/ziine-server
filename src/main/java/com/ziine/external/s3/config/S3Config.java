package com.ziine.external.s3.config;

import com.ziine.external.s3.application.MockS3Service;
import com.ziine.external.s3.application.S3Service;
import com.ziine.external.s3.application.S3ServiceImpl;
import com.ziine.global.AWSProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;

@Configuration
@EnableConfigurationProperties(AWSProperties.class)
public class S3Config {

    @Bean
    @ConditionalOnProperty(name = "spring.cloud.aws.s3.mock", havingValue = "true")
    public S3Service mockS3Service() {
        return new MockS3Service();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.cloud.aws.s3.mock", havingValue = "false", matchIfMissing = true)
    public S3Service s3ServiceImpl(
        final S3Presigner s3Presigner,
        @Qualifier("AWSProperties") final AWSProperties awsProperties
    ) {
        return new S3ServiceImpl(s3Presigner, awsProperties);
    }
}
