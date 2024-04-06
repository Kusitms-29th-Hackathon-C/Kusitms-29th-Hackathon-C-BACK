package com.back.kukertonc.domain.keyword.entity;

import com.back.kukertonc.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "keyword_content")
@Entity
@Setter
public class KeywordContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "keyword_content_id")
    private Long id;
    private String word;
    @ManyToOne
    @JoinColumn(name = "keyword_content_id")
    private KeywordContent keywordContent;
}
