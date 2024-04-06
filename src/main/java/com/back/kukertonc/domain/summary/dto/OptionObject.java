package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class OptionObject {
    private String language;

    public static OptionObject of(
            String language
    ){
        return OptionObject.builder()
                .language(language)
                .build();
    }
}
