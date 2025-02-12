package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import umc.spring.domain.base.BaseEntity;
import umc.spring.domain.enums.MemberGender;
import umc.spring.domain.enums.MemberStatus;
import umc.spring.domain.mapping.MemberPrefer;
import umc.spring.domain.mapping.MissionComplete;

import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Entity
@Builder
@Getter
@DynamicInsert
@DynamicUpdate
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Column(name = "member_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 20)
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'NO'")
    private MemberGender gender;
//    @Column(nullable = false, length = 50)
    private String email;
//    @Column(nullable = false, length = 40)
    private String phone;
    @Column(nullable = false, length = 40)
    private String address;
    @Column(nullable = false, length = 40)
    private String spec_address;
    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(15) DEFAULT 'ACTIVE'")
    private MemberStatus status;
    private LocalDate inactiveDate;
    @ColumnDefault("0")
    private Integer totalPoint;

    /**
     * 연관관계 매핑
     */

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MissionComplete> missionCompleteList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<MemberPrefer> memberPreferList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<Question> questionList = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL)
    private List<PointTransaction> pointTransactionList = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "alarm_id")
    private Alarm alarm;
}
