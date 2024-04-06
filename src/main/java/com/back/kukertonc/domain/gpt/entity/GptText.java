package com.back.kukertonc.domain.gpt.entity;

import com.back.kukertonc.domain.summary.entity.Writing;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Builder
@Document(collection = "gpt_text")
public class GptText {
    private Long writingId;
    private String content;
    public static GptText createGptText(Long writingId, String content){
        return GptText.builder()
                .writingId(writingId)
                .content(content)
                .build();
    }
}

