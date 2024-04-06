package com.back.kukertonc.domain.gpt.dto.response;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class CultureResponse {
    private String prompt;
    private String level;
    private String keyword;
    public static CultureResponse of(String prompt, String level,String keyword){
        return CultureResponse.builder()
                .prompt(prompt)
                .level(level)
                .keyword(keyword)
                .build();
    }
}
