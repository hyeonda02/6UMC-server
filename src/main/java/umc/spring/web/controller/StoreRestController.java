package umc.spring.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Review;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistMember;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping("/add")
    public ApiResponse<StoreResponseDTO.StoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.StoreDTO request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toStoreResultDTO(store));

    }
    @PostMapping("/{storeId}")
    public ApiResponse<StoreResponseDTO.ReviewResultDTO> createReview(@RequestBody @Valid StoreRequestDTO.ReviewDTO request,
                                                                             @ExistStore @PathVariable(name = "storeId") Long storeId,
                                                                             @ExistMember @RequestParam(name = "memberId") Long memberId){
        Review review = storeCommandService.addReview(memberId, storeId, request);
        return ApiResponse.onSuccess(StoreConverter.toReviewResultDTO(review));
    }
}
