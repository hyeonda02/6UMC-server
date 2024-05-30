package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.enums.TransactionStatus;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PointTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_transaction_id")
    private Long id;
    private String content;
    private Integer pointChanged;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionType;

    /** 연관관계 매핑 */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;



}
