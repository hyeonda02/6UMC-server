package umc.spring.converter;

import org.springframework.data.domain.Slice;
import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionResponseDTO;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.MissionDTO toMissionDTO(Mission mission) {
        return MissionResponseDTO.MissionDTO.builder()
                .missionPont(mission.getMissionPoint())
                .deadLine(mission.getDeadLine())
                .content(mission.getContent())
                .build();
    }

    public static MissionResponseDTO.MissionListDTO toMissionListDTO(Slice<Mission> missionList) {
        List<MissionResponseDTO.MissionDTO> missionDTOList =missionList.stream().map(MissionConverter::toMissionDTO).collect(Collectors.toList());
        return MissionResponseDTO.MissionListDTO.builder()
                .hasNext(missionList.hasNext())
                .hasPrevious(missionList.hasPrevious())
                .size(missionList.getSize())
                .missionList(missionDTOList)
                .build();
    }
}