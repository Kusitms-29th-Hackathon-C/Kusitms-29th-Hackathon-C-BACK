package com.back.kukertonc.domain.summary.service;

import com.back.kukertonc.domain.gpt.entity.GptText;
import com.back.kukertonc.domain.gpt.repository.GptTextRepository;
import com.back.kukertonc.domain.summary.dto.*;
import com.back.kukertonc.domain.summary.entity.UserSummary;
import com.back.kukertonc.domain.summary.entity.Writing;
import com.back.kukertonc.domain.summary.repository.UserSummaryRepository;
import com.back.kukertonc.domain.summary.repository.WritingRepository;
import com.back.kukertonc.domain.user.entity.User;
import com.back.kukertonc.domain.user.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
@RequiredArgsConstructor
public class SummaryService {
    @Value("${clova.summary.client-id}")
    private String clientId;
    @Value("${clova.summary.client-secret}")
    private String clientSecret;
    @Value("${clova.summary.url}")
    private String url;
    private final UserSummaryRepository userSummaryRepository;
    private final UserRepository userRepository;
    private final WritingRepository writingRepository;
    private final GptTextRepository gptTextRepository;

    private final RestTemplate restTemplate;

    public UserSummaryResponse postUserSummary(UserSummaryRequest userSummaryRequest) {
        Long userId = userSummaryRequest.getUserId();
        Long writingId = userSummaryRequest.getWritingId();

        User user = userRepository.findById(userId).get();
        Writing writing = writingRepository.findById(writingId).get();

        UserSummary userSummary = UserSummary.of(
                0,
                userSummaryRequest.getMySummary(),
                true,
                user,
                writing
        );

        userSummaryRepository.save(userSummary);

        GptText gptText = gptTextRepository.findByWritingId(writingId);
        String contents = gptText.getContent();

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

        List<UserSummary> userSummaryList = userSummaryRepository.findTop3ByWritingOrderByCreateAt(writing);
        List<Others> othersList = new ArrayList<>();
        for(UserSummary us : userSummaryList){
            if((us.getUser().getId() != userId) && (us.isComplete())){
                Others others = Others.of(us.getUser().getName(), us.getContent());
                try {
                    othersList.add(others);
                }catch (NullPointerException e){
                    break;
                }
            }
        }


        return UserSummaryResponse.of(summaryResult, othersList);
    }

    public TempResponse postUserTemp(UserSummaryRequest userSummaryRequest) {
        Long userId = userSummaryRequest.getUserId();
        Long writingId = userSummaryRequest.getWritingId();

        User user = userRepository.findById(userId).get();
        Writing writing = writingRepository.findById(writingId).get();

        UserSummary userSummary = UserSummary.of(
                0,
                userSummaryRequest.getMySummary(),
                false,
                user,
                writing
        );

        userSummaryRepository.save(userSummary);

        return TempResponse.of("임시저장 완료");
    }
}
