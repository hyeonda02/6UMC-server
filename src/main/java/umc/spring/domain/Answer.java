package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Answer extends BaseEntity {
    @Id
    @Column(name= "answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;

    /** 연관관계 매핑 */
    @OneToOne(mappedBy = "answer", fetch = FetchType.LAZY)
    private Question question;
}





