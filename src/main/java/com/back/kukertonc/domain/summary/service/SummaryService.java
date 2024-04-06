package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.summary.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Slf4j
@Service
public class SummaryService {
    @Value("${clova.summary.client-id}")
    private String clientId;
    @Value("${clova.summary.client-secret}")
    private String clientSecret;
    @Value("${clova.summary.url}")
    private String url;

    private final RestTemplate restTemplate;

    public SummaryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public SummaryResponse getSummary(SummaryRequest summaryRequest) {
        String contents = summaryRequest.getContents();
        contents = contents.replace("\n", "");
        // 요청할 데이터를 정의합니다.
        String requestBody = "{\"document\": {\"content\": \"";
        requestBody += contents;
        requestBody += "\"},\"option\": {\"language\": \"ko\"}}";

        log.info(requestBody);

        // 헤더를 정의합니다.
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-NCP-APIGW-API-KEY-ID", clientId);
        headers.set("X-NCP-APIGW-API-KEY", clientSecret);

        // 헤더와 바디를 가진 HTTP 요청을 생성합니다.
        HttpEntity<String> requestEntity = new HttpEntity<>(requestBody, headers);

        // 외부 API를 호출합니다.
        String apiUrl = "https://naveropenapi.apigw.ntruss.com/text-summary/v1/summarize";

        String result = restTemplate.postForObject(apiUrl, requestEntity, String.class);
        String[] resultSlice = result.split(":");
        String summaryResult = resultSlice[resultSlice.length -1].replace("\"", "");
        summaryResult = summaryResult.replace("}", "");

        return SummaryResponse.of(summaryResult);
    }
}
