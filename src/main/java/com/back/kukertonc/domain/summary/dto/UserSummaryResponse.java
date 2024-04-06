package com.back.kukertonc.domain.summary.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.print.attribute.standard.MediaSize;
import java.util.List;

@Getter
@Builder
public class UserSummaryResponse {
    private String summary;
    private List<Others> others;

    public static UserSummaryResponse of(
            String summary,
            List<Others> others
    ){
        return UserSummaryResponse.builder()
                .summary(summary)
                .others(others)
                .build();
    }
}
