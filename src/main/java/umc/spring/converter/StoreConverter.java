package umc.spring.converter;

import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

import java.time.LocalDateTime;

public class StoreConverter {

    public static StoreResponseDTO.StoreResultDTO toStoreResultDTO(Store store) {
        return StoreResponseDTO.StoreResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }
    public static Store toStore(StoreRequestDTO.StoreDTO request) {
        return Store.builder()
                .storeAddress(request.getStoreAddress())
                .storeName(request.getStoreName())
                .build();
    }

    public static StoreResponseDTO.ReviewResultDTO toReviewResultDTO(Review review) {
        return StoreResponseDTO.ReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .reviewContent(request.getReviewContent())
                .reviewGrade(request.getReviewGrade())
                .build();
    }

}
