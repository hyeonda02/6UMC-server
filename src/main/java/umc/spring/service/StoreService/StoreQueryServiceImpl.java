package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreResponseDTO;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService{
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);

    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {
        Store store = storeRepository.findById(storeId).get();
        return reviewRepository.findAllByStore(store, PageRequest.of(page, 5));
    }

    @Override
    public StoreResponseDTO.ReviewPreViewListDTO getReviewPageList(Page<Review> reviewList) {
        return StoreConverter.reviewPreViewListDTO(reviewList);
    }
}
