package com.back.kukertonc.domain.gpt.entity;

import com.back.kukertonc.domain.summary.entity.Writing;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;


@Data
@Builder
@ToString
@Document(collection = "gpt_text")
public class GptText {
    @Id
    private String id;
    @Field(name = "writingId")
    private Long writingId;
    private String content;
    public static GptText createGptText(Long writingId, String content){
        return GptText.builder()
                .writingId(writingId)
                .content(content)
                .build();
    }
}

