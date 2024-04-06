package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class SummaryResponse {
    private String summary;

    public static SummaryResponse of(
            String summary
    ){
        return SummaryResponse.builder()
                .summary(summary)
                .build();
    }
}
