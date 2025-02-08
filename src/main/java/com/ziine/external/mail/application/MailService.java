package com.ziine.external.mail.application;

public interface MailService {

    void sendMail(
        final String recipientEmail,
        final String subject,
        final String content
    );
}
