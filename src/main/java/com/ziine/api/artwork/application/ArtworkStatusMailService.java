package com.ziine.api.artwork.application;

import com.ziine.api.artwork.domain.entity.ArtworkStatus;
import com.ziine.api.artwork.domain.entity.ArtworkStatusHistoryEntity;
import com.ziine.external.mail.application.MailService;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@RequiredArgsConstructor
@Service
public class ArtworkStatusMailService {

    private final MailService mailService;
    private final TemplateEngine templateEngine;

    public void sendArtworkStatusChangeMail(final ArtworkStatusHistoryEntity artworkStatusHistoryEntity) {
        final String recipientEmail = getRecipientEmail(artworkStatusHistoryEntity);
        if (StringUtils.isBlank(recipientEmail)) {
            return;
        }

        final ArtworkStatus toStatus = artworkStatusHistoryEntity.getToStatus();
        final String subject = getSubjectByArtworkStatus(toStatus);
        final String templateName = getTemplateNameByArtworkStatus(toStatus);
        final Map<String, Object> variables = prepareTemplateVariables(artworkStatusHistoryEntity);

        final String content = generateMailContent(templateName, variables);
        mailService.sendMail(recipientEmail, subject, content);
    }

    private String getRecipientEmail(final ArtworkStatusHistoryEntity artworkStatusHistoryEntity) {
        return artworkStatusHistoryEntity.getArtworkEntity()
            .getArtistEntity()
            .getEmail();
    }

    private String getSubjectByArtworkStatus(final ArtworkStatus artworkStatus) {
        return switch (artworkStatus) {
            case PENDING -> "작품 검토 중입니다";
            case APPROVED -> "작품이 승인되었습니다";
            case REJECTED -> "작품이 반려되었습니다";
            default -> throw new IllegalArgumentException("잘못된 상태: " + artworkStatus);  // Business Exception이 아님
        };
    }

    private String getTemplateNameByArtworkStatus(final ArtworkStatus artworkStatus) {
        final String templateName = switch (artworkStatus) {
            case PENDING -> "pending";
            case APPROVED -> "approved";
            case REJECTED -> "rejected";
            default -> throw new IllegalArgumentException("잘못된 상태: " + artworkStatus);  // Business Exception이 아님
        };

        return "artwork-" + templateName;
    }

    private Map<String, Object> prepareTemplateVariables(final ArtworkStatusHistoryEntity artworkStatusHistoryEntity) {
        final Map<String, Object> variables = new HashMap<>();
        final String artistName = artworkStatusHistoryEntity.getArtworkEntity()
            .getArtistEntity()
            .getName();
        variables.put("artistName", artistName);

        if (artworkStatusHistoryEntity.getToStatus() == ArtworkStatus.APPROVED) {
            final String artworkUrl = "https://www.ziine.gallery/artwork/" +
                artworkStatusHistoryEntity.getArtworkEntity()
                    .getId();
            variables.put("artworkUrl", artworkUrl);
        }

        if (artworkStatusHistoryEntity.getToStatus() == ArtworkStatus.REJECTED) {
            variables.put("rejectionReason", artworkStatusHistoryEntity.getRejectionReason());
        }

        return variables;
    }

    private String generateMailContent(
        final String templateName,
        final Map<String, Object> variables
    ) {  // TODO. 추후 html 템플릿 수정
        final Context context = new Context();
        context.setVariables(variables);
        return templateEngine.process(templateName, context);
    }
}
