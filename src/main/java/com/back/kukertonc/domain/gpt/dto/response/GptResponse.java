package com.back.kukertonc.domain.gpt.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class GptResponse {
    private String title;
    private String content;
    public static GptResponse of(String title, String content){
        return GptResponse.builder()
                .title(title)
                .content(content)
                .build();
    }
}
