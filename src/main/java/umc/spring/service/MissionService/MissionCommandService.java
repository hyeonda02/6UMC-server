package umc.spring.service.MissionService;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;

public interface MissionCommandService {
    public Mission addMission(Long storeId, MissionRequestDTO.missionDTO request);

}
