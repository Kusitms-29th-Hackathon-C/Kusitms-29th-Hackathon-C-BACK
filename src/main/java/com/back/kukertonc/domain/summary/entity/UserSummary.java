package com.back.kukertonc.domain.summary.entity;

import com.back.kukertonc.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Getter
@Table(name = "user_summary")
@Entity
@Setter
public class UserSummary {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_summary_id")
    private Long id;
    private int recommend;
    private String content;
    private boolean isComplete;
    @CreationTimestamp
    private Timestamp createAt;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @ManyToOne
    @JoinColumn(name = "writing_id")
    private Writing writing;


    public static UserSummary of(
        int recommend,
        String content,
        boolean isComplete,
        User user,
        Writing writing
    ){
        return UserSummary.builder()
                .recommend(recommend)
                .content(content)
                .isComplete(isComplete)
                .user(user)
                .writing(writing)
                .build();
    }
}
