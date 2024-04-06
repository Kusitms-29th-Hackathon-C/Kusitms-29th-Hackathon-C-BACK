package com.back.kukertonc.domain.gpt.service;

import com.back.kukertonc.domain.gpt.dto.request.WritingRequestDto;
import com.back.kukertonc.domain.gpt.dto.response.CultureResponse;
import com.back.kukertonc.domain.gpt.entity.GptText;
import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.service.WritingAppender;
import com.back.kukertonc.global.config.RestTemplateConfig;
import com.back.kukertonc.domain.gpt.dto.request.ChatCompletionDto;
import com.back.kukertonc.domain.gpt.dto.request.ChatRequestMsgDto;
import com.back.kukertonc.domain.gpt.dto.response.GptResponse;
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
import org.springframework.transaction.annotation.Transactional;
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
    private final WritingAppender writingAppender;
    private final GptTextAppender gptTextAppender;
    @Value("${openai.url.prompt}")
    private String promptUrl;

    public  List<String>  prompt(String type) throws IOException {
        String[] contents;
        List<String> words = new ArrayList<>();
        Map<String, Object> resultMap = new HashMap<>();

        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = restTemplateConfig.httpHeaders();
        List<ChatRequestMsgDto> messages = new ArrayList<>();

        String prompt = readPromptFromFile(null,type);
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
    @Transactional
    public List<GptResponse> getTextCulture(String type) throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        List<GptResponse> gptResponses = new ArrayList<>();
        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = restTemplateConfig.httpHeaders();
        List<ChatRequestMsgDto> messages = new ArrayList<>();
        for(int i =1; i<4;i++){
            CultureResponse keyword = getCultureKeyword(i);
            CultureResponse prompt = readCulturePromptFromFile(keyword);
            String editPrompt = prompt.getPrompt().replace("(키워드)", keyword.getKeyword());
            messages.add(createChatRequestMsgDto("user", editPrompt));


            //요청 프롬프트 설정
            messages.get(0).setContent(editPrompt);

            // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
            resultMap = confRestTemplate(messages,headers);

            GptResponse gptResponse = getText(resultMap);
            gptResponses.add(gptResponse);
            gptTextAppender.save(GptText.createGptText(writingAppender.save(Writing.createWriting(gptResponse.getTitle(),"상", "사회 문화",keyword.getKeyword() )).getId(),gptResponse.getContent()));
        }

        return gptResponses;
    }
    @Transactional
    public List<GptResponse> getTextTech() throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        List<GptResponse> gptResponses = new ArrayList<>();
        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = restTemplateConfig.httpHeaders();
        List<ChatRequestMsgDto> messages = new ArrayList<>();
        for(int i =1; i<4;i++){
            CultureResponse keyword = getTechKeyword(i);
            CultureResponse prompt = readCulturePromptFromFile(keyword);
            String editPrompt = prompt.getPrompt().replace("(키워드)", keyword.getKeyword());
            messages.add(createChatRequestMsgDto("user", editPrompt));


            //요청 프롬프트 설정
            messages.get(0).setContent(editPrompt);

            // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
            resultMap = confRestTemplate(messages,headers);

            GptResponse gptResponse = getText(resultMap);
            gptResponses.add(gptResponse);
            gptTextAppender.save(GptText.createGptText(writingAppender.save(Writing.createWriting(gptResponse.getTitle(),"상", "사회 문화",keyword.getKeyword() )).getId(),gptResponse.getContent()));
        }

        return gptResponses;
    }
    @Transactional
    public List<GptResponse> getTextArt() throws IOException {
        Map<String, Object> resultMap = new HashMap<>();
        List<GptResponse> gptResponses = new ArrayList<>();
        // [STEP1] 토큰 정보가 포함된 Header를 가져옵니다.
        HttpHeaders headers = restTemplateConfig.httpHeaders();
        List<ChatRequestMsgDto> messages = new ArrayList<>();
        for(int i =1; i<4;i++){
            CultureResponse keyword = getArtKeyword(i);
            CultureResponse prompt = readCulturePromptFromFile(keyword);
            String editPrompt = prompt.getPrompt().replace("(키워드)", keyword.getKeyword());
            messages.add(createChatRequestMsgDto("user", editPrompt));


            //요청 프롬프트 설정
            messages.get(0).setContent(editPrompt);

            // [STEP5] 통신을 위한 RestTemplate을 구성합니다.
            resultMap = confRestTemplate(messages,headers);

            GptResponse gptResponse = getText(resultMap);
            gptResponses.add(gptResponse);
            gptTextAppender.save(GptText.createGptText(writingAppender.save(Writing.createWriting(gptResponse.getTitle(),"상", "사회 문화",keyword.getKeyword() )).getId(),gptResponse.getContent()));
        }

        return gptResponses;
    }
    private String getKeyword(){
        return "코나투스";
    }
    private CultureResponse getCultureKeyword(int count){
        if(count==1)
            return CultureResponse.of(null, "상","아웃 소싱");
        if(count==2)
            return CultureResponse.of(null, "중","경제 성장 모형");
        return CultureResponse.of(null, "하","기본권과 제도 보장");
    }
    private CultureResponse getTechKeyword(int count){
        if(count==1)
            return CultureResponse.of(null, "상","인공지능과 기계학습");
        if(count==2)
            return CultureResponse.of(null, "중","암세포의 증식");
        return CultureResponse.of(null, "하","블록체인과 암호화폐");
    }
    private CultureResponse getArtKeyword(int count){
        if(count==1)
            return CultureResponse.of(null, "상","코나투스");
        if(count==2)
            return CultureResponse.of(null, "중","지엘의 예술론");
        return CultureResponse.of(null, "하","에스포지토의 주권과 면역");
    }
    private String readPromptFromFile(String keyword, String type) throws IOException {
        ClassPathResource resource = null;
            
        if(type.equals("word"))
            resource = new ClassPathResource("prompts/word.txt");
        if(type.equals("text"))
            resource = new ClassPathResource(selectPrompt(keyword));
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bytes, StandardCharsets.UTF_8);

    }
    private CultureResponse readCulturePromptFromFile(CultureResponse keyword) throws IOException {
        ClassPathResource resource = null;

        resource = new ClassPathResource(selectPrompt(keyword.getKeyword()));
        byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return CultureResponse.of(new String(bytes, StandardCharsets.UTF_8), keyword.getLevel(), keyword.getKeyword());

    }
    private String selectPrompt(String keyword){
        if(keyword.equals("아웃 소싱")||keyword.equals("인공지능과 기계학습")||keyword.equals("코나투스"))
            return "prompts/high.txt";
        if(keyword.equals("경제 성장 모형")||keyword.equals("암세포의 증식")||keyword.equals("지엘의 예술론"))
            return "prompts/mid.txt";
        if(keyword.equals("기본권과 제도 보장")||keyword.equals("블록체인과 암호화폐")||keyword.equals("에스포지토의 주권과 면역"))
            return "prompts/low.txt";
        return "";
    }


    private Map<String, Object> confRestTemplate(List<ChatRequestMsgDto> messages, HttpHeaders headers) throws JsonProcessingException {
        Map<String, Object> resultMap = new HashMap<>();
        HttpEntity<ChatCompletionDto> requestEntity = new HttpEntity<>(createChatCompletionDto("gpt-4", messages), headers);
        ResponseEntity<String> response = chatGPTConfig
                .exchange(promptUrl, HttpMethod.POST, requestEntity, String.class);
        ObjectMapper om = new ObjectMapper();
        resultMap = om.readValue(response.getBody(), new TypeReference<>() {
        });
        return resultMap;
    }
    private GptResponse getText(Map<String, Object> resultMap){
        String firstLine = "";
        String remainingLines="";
        if (resultMap.containsKey("choices")) {
            List<Map<String, Object>> choices = (List<Map<String, Object>>) resultMap.get("choices");
            for (Map<String, Object> choice : choices) {
                Map<String, Object> message = (Map<String, Object>) choice.get("message");
                if (message.containsKey("content")) {
                    String content = (String) message.get("content");
                    String[] lines = content.split("\\r?\\n", 2);

                    firstLine = lines[0];
                    remainingLines = "";

                    if (lines.length > 1) {
                        remainingLines = lines[1];
                    }
                    if (firstLine.startsWith("제목 : ")) {
                        firstLine = firstLine.substring(5); // "제목 : " 부분 제거
                    }

                    if (lines.length > 1) {
                        remainingLines = lines[1];
                        if (remainingLines.startsWith("\n내용 : ")) {
                            remainingLines = remainingLines.substring(6); // "내용 : " 부분 제거
                        }
                    }
                }
            }
        }
        return GptResponse.of(firstLine, remainingLines);
    }
}
