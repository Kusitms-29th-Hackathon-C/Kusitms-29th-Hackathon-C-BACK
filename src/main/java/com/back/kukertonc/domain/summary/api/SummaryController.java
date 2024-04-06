package com.back.kukertonc.domain.summary.api;

import com.back.kukertonc.domain.summary.dto.SummaryRequest;
import com.back.kukertonc.domain.summary.dto.SummaryResponse;
import com.back.kukertonc.domain.summary.dto.UserSummaryRequest;
import com.back.kukertonc.domain.summary.dto.UserSummaryResponse;
import com.back.kukertonc.domain.summary.service.SummaryService;
import com.back.kukertonc.global.reponse.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/summary")
@RequiredArgsConstructor
public class SummaryController {
    private final SummaryService summaryService;

    @PostMapping("/user")
    public BaseResponse<UserSummaryResponse> postUserSummary(@RequestBody UserSummaryRequest userSummaryRequest){
        return new BaseResponse<>(summaryService.postUserSummary(userSummaryRequest));
    }
}
