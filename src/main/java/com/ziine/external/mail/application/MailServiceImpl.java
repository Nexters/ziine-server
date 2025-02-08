package com.ziine.external.mail.application;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;

@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

    private final JavaMailSender javaMailSender;

    @Async
    @Override
    public void sendMail(
        final String recipientEmail,
        final String subject,
        final String content
    ) {
        try {
            if (StringUtils.isBlank(recipientEmail)) {
                throw new IllegalArgumentException(
                    "Recipient email must not be null or blank");
            }

            final MimeMessage mimeMessage = createMimeMessage(recipientEmail, subject, content);
            javaMailSender.send(mimeMessage);
        } catch (Exception exception) {
            throw new RuntimeException("Failed to send email", exception);  // Business Exception이 아님
        }
    }

    private MimeMessage createMimeMessage(
        final String recipientEmail,
        final String subject,
        final String content
    ) throws MessagingException {
        final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        final MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        helper.setTo(recipientEmail);
        helper.setSubject(subject);
        helper.setText(content, true);

        return mimeMessage;
    }
}
