package umc.spring.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import jakarta.persistence.*;
import lombok.*;
import umc.spring.domain.base.BaseEntity;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {
    @Id
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String storeName;
    private Float storeTotalGrade;
    private String storeAddress;

    /**
     * 연관관계 매핑
     */
    @OneToMany(mappedBy = "store")
    private List<Review> reviewList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL)
    private List<Mission> missionList = new ArrayList<>();
}
