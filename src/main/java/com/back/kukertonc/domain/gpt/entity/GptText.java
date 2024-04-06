package com.back.kukertonc.domain.gpt.entity;

import lombok.Builder;
import lombok.Getter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Getter
@Builder
@Document(collection = "gpt_text")
public class GptText {

    private Long writingId;
    private String content;
    public static GptText createGptText(Long writingId, String content){
        return  GptText.builder()
                .writingId(writingId)
                .content(content)
                .build();
    }
}

