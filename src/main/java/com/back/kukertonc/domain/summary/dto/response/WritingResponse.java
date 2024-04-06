package com.back.kukertonc.domain.summary.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
public class WritingResponse {
    private String type;
    private List<WritingInfoResponse> infoList;
    public static WritingResponse of(String type,List<WritingInfoResponse> infoList){
        return WritingResponse.builder()
                .type(type)
                .infoList(infoList)
                .build();
    }
}
