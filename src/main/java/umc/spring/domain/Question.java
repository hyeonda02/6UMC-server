package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.enums.QuestionStatus;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Question extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long id;
    private String questionTitle;
    private String questionContent;
    @Enumerated(EnumType.STRING)
    private QuestionStatus questionStatus;
    /**
     * 연관관계 매핑
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<QuestionImage> questionImageList = new ArrayList<>();

    @OneToOne
    @JoinColumn(name="answer_id")
    private Answer answer;
}
