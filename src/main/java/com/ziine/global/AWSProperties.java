package com.ziine.global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "spring.cloud.aws")
public class AWSProperties {

    private final S3 s3 = new S3();
    private final Cdn cdn = new Cdn();

    @Getter
    @Setter
    public static class S3 {

        private String bucket;
    }

    @Getter
    @Setter
    public static class Cdn {

        private String url;
    }
}
