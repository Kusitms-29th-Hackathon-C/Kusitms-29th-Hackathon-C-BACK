package com.back.kukertonc.domain.summary.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ClovaSummaryRequest {
    private DocumentObject documentObject;
    private OptionObject optionObject;

    public static ClovaSummaryRequest of(
            DocumentObject documentObject,
            OptionObject optionObject
    ){
        return ClovaSummaryRequest.builder()
                .documentObject(documentObject)
                .optionObject(optionObject)
                .build();
    }
}
