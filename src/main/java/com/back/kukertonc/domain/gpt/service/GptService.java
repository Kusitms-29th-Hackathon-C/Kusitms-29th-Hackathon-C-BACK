package com.back.kukertonc.domain.gpt.service;

import com.back.kukertonc.config.RestTemplateConfig;
import com.back.kukertonc.domain.gpt.dto.request.ChatCompletionDto;
import com.back.kukertonc.domain.gpt.dto.request.ChatRequestMsgDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.back.kukertonc.domain.gpt.dto.request.ChatCompletionDto.createChatCompletionDto;
import static com.back.kukertonc.domain.gpt.dto.request.ChatRequestMsgDto.createChatRequestMsgDto;

@Service
@RequiredArgsConstructor
public class GptService {
    private final RestTemplate chatGPTConfig;
    private final RestTemplateConfig restTemplateConfig;
    @Value("${openai.url.prompt}")
    private String promptUrl;

    public  List<String>  prompt() throws IOException {
        String[] contents;
        List<String> words = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = restTemplateConfig.httpHeaders();
        List<ChatRequestMsgDto> messages = new ArrayList<>();

        String prompt = readPromptFromFile();
        messages.add(createChatRequestMsgDto("user", prompt));



        messages.get(0).setContent(prompt);
        // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
        HttpEntity<ChatCompletionDto> requestEntity = new HttpEntity<>(createChatCompletionDto("gpt-3.5-turbo", messages), headers);
        ResponseEntity<String> response = chatGPTConfig
                .exchange(promptUrl, HttpMethod.POST, requestEntity, String.class);

        // [STEP6] String -> HashMap 역직렬화를 구성합니다.
            ObjectMapper om = new ObjectMapper();
            resultMap = om.readValue(response.getBody(), new TypeReference<>() {
            });
            // 데이터 추출 및 저장
            if (resultMap.containsKey("choices")) {
                List<Map<String, Object>> choices = (List<Map<String, Object>>) resultMap.get("choices");
                for (Map<String, Object> choice : choices) {
                    Map<String, Object> message = (Map<String, Object>) choice.get("message");
                    if (message.containsKey("content")) {
                        String content = (String) message.get("content");
                        contents = content.split("\\s+"); // 공백을 기준으로 단어 분리

                        String word1 = contents[0];
                        String word2 = contents[1];
                        String word3 = contents[2];
                        words.add(word1);
                        words.add(word2);
                        words.add(word3);
                    }
                }
            }

        return words;
    }

    private String readPromptFromFile() throws IOException {
        ClassPathResource resource;

            resource = new ClassPathResource("prompts/word.txt");

            byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
            return new String(bytes, StandardCharsets.UTF_8);

    }
}