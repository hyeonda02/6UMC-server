package umc.spring.web.dto;

import jakarta.persistence.Access;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

public class MissionResponseDTO {
    @Builder
    @Getter
    @AllArgsConstructor
    @RequiredArgsConstructor
    public static class missionResultDTO{
        Long missionId;
        LocalDateTime createdAt;

    }
}
