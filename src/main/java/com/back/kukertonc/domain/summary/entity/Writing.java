package com.back.kukertonc.domain.summary.entity;

import com.back.kukertonc.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "writing")
@Entity
@Setter
public class Writing extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "writing_id")
    private Long id;
    private String title;
    private String level;

}
