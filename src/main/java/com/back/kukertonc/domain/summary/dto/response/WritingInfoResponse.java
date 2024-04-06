package com.back.kukertonc.domain.summary.dto.response;

import com.back.kukertonc.domain.summary.entity.Writing;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WritingInfoResponse {
    private Long writingId;
    private String keyword;
    private boolean isComplete;
    public static WritingInfoResponse of(Writing writing, boolean isComplete){
        return WritingInfoResponse.builder()
                .writingId(writing.getId())
                .keyword(writing.getKeyword())
                .isComplete(isComplete)
                .build();
    }
}
