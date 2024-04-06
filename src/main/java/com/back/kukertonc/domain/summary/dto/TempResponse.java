package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TempResponse {
    private String result;

    public static TempResponse of(
            String result
    ){
        return TempResponse.builder()
                .result(result)
                .build();
    }
}
