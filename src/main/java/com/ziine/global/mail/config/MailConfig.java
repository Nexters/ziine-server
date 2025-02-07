package com.ziine.global.mail.config;

import com.ziine.global.mail.application.MailService;
import com.ziine.global.mail.application.MailServiceImpl;
import com.ziine.global.mail.application.MockMailService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

@Configuration
public class MailConfig {

    @Bean
    @ConditionalOnProperty(name = "spring.mail.mock", havingValue = "true")
    public MailService mockMailService() {
        return new MockMailService();
    }

    @Bean
    @ConditionalOnProperty(name = "spring.mail.mock", havingValue = "false", matchIfMissing = true)
    public MailService mailServiceImpl(final JavaMailSender javaMailSender) {
        return new MailServiceImpl(javaMailSender);
    }
}
