package com.back.kukertonc.domain.keyword.entity;

import com.back.kukertonc.global.common.BaseTimeEntity;
import com.back.kukertonc.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user_keyword")
@Entity
@Setter
public class UserKeyword extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_keyword_id")
    private Long id;
    private String title;
    private String content;
    private boolean isComplete;
    @ManyToOne
    @JoinColumn(name = "keyword_id")
    private Keyword keyword;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
