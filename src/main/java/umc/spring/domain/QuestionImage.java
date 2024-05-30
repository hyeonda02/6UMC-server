package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class QuestionImage {
    @Id
    @Column(name = "question_image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String imageURL;
    /**
     * 연관관계 매핑
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id")
    private Question question;
}
