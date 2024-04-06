package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DocumentObject {
    private String content;

    public static DocumentObject of(
            String content
    ){
        return DocumentObject.builder()
                .content(content)
                .build();
    }
}
