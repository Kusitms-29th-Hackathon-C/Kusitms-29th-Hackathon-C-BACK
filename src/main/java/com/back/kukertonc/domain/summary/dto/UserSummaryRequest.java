package com.back.kukertonc.domain.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class UserSummaryRequest {
    private Long userId;
    private Long writingId;
    private String summary;
    private Boolean isComplete;
}
