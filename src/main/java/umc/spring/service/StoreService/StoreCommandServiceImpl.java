package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Member;
import umc.spring.domain.Region;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.MemberRepository;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.ReviewRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;

    @Override
    @Transactional
    public Review addReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request) {
        Review review = StoreConverter.toReview(request);

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        return reviewRepository.save(review);

    }

    @Override
    @Transactional
    public Store addStore(StoreRequestDTO.StoreDTO request) {
        Store store = StoreConverter.toStore(request);
        String regionName = extractRegionNameFromAddress(request.getStoreAddress());
        Region region = regionRepository.findRegionIdByRegionName(regionName);

        if (region == null) {
            new RegionHandler(ErrorStatus.REGION_NOT_FOUND);
        }

        store.setRegion(region);
        return storeRepository.save(store);
    }

    @Transactional
    public String extractRegionNameFromAddress(String storeAddress) {
        if (storeAddress.contains("구")) {
            int startIndex = storeAddress.indexOf(" ") + 1;
            int endIndex = storeAddress.indexOf("구") + 1;
            return storeAddress.substring(startIndex, endIndex);
        }
        throw new RegionHandler(ErrorStatus.REGION_NOT_FOUND);
    }
}
