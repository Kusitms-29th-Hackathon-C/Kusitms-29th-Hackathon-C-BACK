package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Others {
    private String nickName;
    private String content;

    public static Others of(
            String nickName,
            String content
    ){
        return Others.builder()
                .nickName(nickName)
                .content(content)
                .build();
    }
}
