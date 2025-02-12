package umc.spring.service.StoreService;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;

public interface StoreCommandService {
    Review addReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request);
    public Store addStore(StoreRequestDTO.StoreDTO request);

}
