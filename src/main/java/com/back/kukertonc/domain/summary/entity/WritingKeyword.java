package com.back.kukertonc.domain.summary.entity;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "writing_keyword")
@Entity
@Setter
public class WritingKeyword {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writing_keyword_id")
    private Long id;
    private String keyword;
    private String type;
    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;
}
