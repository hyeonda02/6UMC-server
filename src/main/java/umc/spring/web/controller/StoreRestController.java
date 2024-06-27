package umc.spring.web.controller;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.converter.MissionConverter;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Mission;
import umc.spring.domain.Store;
import umc.spring.repository.StoreRepository;
import umc.spring.service.MissionService.MissionCommandService;
import umc.spring.service.StoreService.StoreCommandService;
import umc.spring.validation.annotation.ExistStore;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;
import umc.spring.web.dto.StoreRequestDTO;
import umc.spring.web.dto.StoreResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final MissionCommandService missionCommandService;
    @PostMapping("/add")
    public ApiResponse<StoreResponseDTO.StoreResultDTO> addStore(@RequestBody @Valid StoreRequestDTO.StoreDTO request) {
        Store store = storeCommandService.addStore(request);
        return ApiResponse.onSuccess(StoreConverter.toStoreResultDTO(store));

    }

    @PostMapping("/{storeId}/mission/add")
    public ApiResponse<MissionResponseDTO.missionResultDTO> addMission(@RequestBody @Valid MissionRequestDTO.missionDTO request,
                                                                            @ExistStore @PathVariable(name="storeId")Long storeId) {
        Mission mission = missionCommandService.addMission(storeId, request);
        return ApiResponse.onSuccess(MissionConverter.toMissionResultDTO(mission));

    }
}
