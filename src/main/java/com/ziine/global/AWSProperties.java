package com.ziine.global;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Primary
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.aws")
public class AWSProperties {

    private S3 s3;
    private Cdn cdn;

    @Getter
    @RequiredArgsConstructor
    public static class S3 {

        private final String bucket;
    }

    @Getter
    @RequiredArgsConstructor
    public static class Cdn {

        private final String url;
    }
}
