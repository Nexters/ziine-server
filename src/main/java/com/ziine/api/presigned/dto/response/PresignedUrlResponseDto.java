package com.ziine.api.presigned.dto.response;

import com.ziine.api.presigned.dto.PresignedUrl;
import java.util.List;

public record PresignedUrlResponseDto(List<PresignedUrl> presignedUrlList) {

}
