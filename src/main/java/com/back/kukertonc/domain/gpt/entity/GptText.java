package com.back.kukertonc.domain.gpt.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Builder
@Document(collection = "gpt_code")
public class GptText {
    private Long gptId;
    private String content;
}

