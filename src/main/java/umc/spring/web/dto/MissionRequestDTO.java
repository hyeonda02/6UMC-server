package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class MissionRequestDTO {
    @Getter
    public static class missionDTO{
        @NotNull
        int missionPoint;
        @NotNull
        String missionContent;

    }
}
