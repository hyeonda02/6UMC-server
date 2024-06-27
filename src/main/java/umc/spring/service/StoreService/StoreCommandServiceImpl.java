package umc.spring.service.StoreService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.apiPayload.code.status.ErrorStatus;
import umc.spring.apiPayload.exception.handler.RegionHandler;
import umc.spring.converter.StoreConverter;
import umc.spring.domain.Region;
import umc.spring.domain.Store;
import umc.spring.repository.RegionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.StoreRequestDTO;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreCommandServiceImpl implements StoreCommandService {
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
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
