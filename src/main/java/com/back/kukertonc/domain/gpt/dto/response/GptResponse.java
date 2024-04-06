package com.back.kukertonc.domain.gpt.dto.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;
@Builder
@Getter
public class GptResponse {
    private List<String> words;

}
