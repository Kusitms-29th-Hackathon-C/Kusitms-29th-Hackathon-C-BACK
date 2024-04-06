package com.back.kukertonc.domain.gpt.dto.request;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Setter
public class ChatRequestMsgDto {

    private String role;

    private String content;

    @Builder
    public ChatRequestMsgDto(String role, String content) {
        this.role = role;
        this.content = content;
    }
    public static ChatRequestMsgDto createChatRequestMsgDto(String role, String content){
        ChatRequestMsgDto chatRequestMsgDto = ChatRequestMsgDto.builder()
                .role(role)
                .content(content)
                .build();
        return chatRequestMsgDto;

    }
}
