package umc.spring.domain;

import jakarta.persistence.*;
import lombok.*;
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
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Member extends BaseEntity {
    @Column(name = "member_id")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private MemberGender gender;
    private String email;
    private String phone;
    private String address;
    private String spec_address;
    private String birth;
    @Enumerated(EnumType.STRING)
    private MemberStatus status;
    private LocalDate inactiveDate;
    private Integer titalPoint;

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
