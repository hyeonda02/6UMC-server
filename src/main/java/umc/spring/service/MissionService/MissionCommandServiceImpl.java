package umc.spring.service.MissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.converter.MissionConverter;
import umc.spring.domain.Mission;
import umc.spring.repository.MissionRepository;
import umc.spring.repository.StoreRepository;
import umc.spring.web.dto.MissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionCommandServiceImpl implements MissionCommandService{
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;
    @Override
    @Transactional
    public Mission addMission(Long storeId, MissionRequestDTO.missionDTO request) {
        Mission mission = MissionConverter.toMission(request);
        mission.setStore(storeRepository.findById(storeId).get());
        return missionRepository.save(mission);
    }
}
