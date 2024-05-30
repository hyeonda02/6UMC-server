package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Alarm extends BaseEntity {
    @Id @Column(name= "alarm_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean eventAlarm;
    private Boolean reviewAlarm;
    private Boolean questionAlarm;


    /** 연관관계 매핑 OnetoOne */

    @OneToOne(mappedBy = "alarm",fetch = FetchType.LAZY)
    private Member member;

}
