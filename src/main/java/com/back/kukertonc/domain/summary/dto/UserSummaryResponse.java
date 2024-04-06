package com.back.kukertonc.domain.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class UserSummaryResponse {
    private Boolean isComplete;

    public static UserSummaryResponse of(
            Boolean isComplete
    ){
        return UserSummaryResponse.builder()
                .isComplete(isComplete)
                .build();
    }
}
