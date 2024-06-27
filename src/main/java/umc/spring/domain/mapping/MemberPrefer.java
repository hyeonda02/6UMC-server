package umc.spring.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.FoodCategory;
import umc.spring.domain.Member;
import umc.spring.domain.base.BaseEntity;

@Builder
@Getter
@Entity
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberPrefer extends BaseEntity {

    @Id
    @Column(name = "member_prefer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 연관관계 매핑
     **/
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private FoodCategory foodCategory;

    //연관관계 편의 메서드
    public void setMember(Member member) {
        if (this.member != null) {
            member.getMemberPreferList().remove(this);
        }
        this.member = member;
        member.getMemberPreferList().add(this);
    }

    public void setFoodCategory(FoodCategory foodCategory) {
        this.foodCategory = foodCategory;
    }
}
