package com.ziine.external.mail.application;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class MockMailService implements MailService {

    @Override
    public void sendMail(
        final String recipientEmail,
        final String subject,
        final String content
    ) {
        log.info("[Mock Email] To: {} | Subject: {} | Content: {}", recipientEmail, subject, content);
    }
}
