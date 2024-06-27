package umc.spring.converter;

import umc.spring.domain.Mission;
import umc.spring.web.dto.MissionRequestDTO;
import umc.spring.web.dto.MissionResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionConverter {
    public static Mission toMission(MissionRequestDTO.missionDTO request) {
        return Mission.builder()
                .missionPoint(request.getMissionPoint())
                .deadLine(LocalDate.from(request.getDeadLine()))
                .content(request.getMissionContent())
                .build();
    }

    public static MissionResponseDTO.missionResultDTO toMissionResultDTO(Mission mission) {
        return MissionResponseDTO.missionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();

    }
}
