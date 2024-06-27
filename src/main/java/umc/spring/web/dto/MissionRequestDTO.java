package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionRequestDTO {
    @Getter
    public static class missionDTO{
        @NotNull
        int missionPoint;
        @NotNull
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate deadLine;
        @NotNull
        String missionContent;

    }
}
