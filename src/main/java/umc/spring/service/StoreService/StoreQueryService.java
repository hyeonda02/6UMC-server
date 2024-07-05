package umc.spring.service.StoreService;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Slice;
import umc.spring.domain.Mission;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.Optional;

public interface StoreQueryService {
    Optional<Store> findStore(Long id);
    Page<Review>  getReviewList(Long storeId, Integer page);
    StoreResponseDTO.ReviewPreViewListDTO getReviewPageList(Page<Review> reviewList);

    Slice<Mission> getMissionList(Long storeId, Integer page);
    MissionResponseDTO.MissionListDTO getMissionDTOList(Slice<Mission> missionList);

}