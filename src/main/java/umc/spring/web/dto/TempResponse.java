package umc.spring.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class TempResponse { //다양한 응답들을 감싸는 역할
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempTestDTO{ //TempTestDto는 구체적인 응답데이터 구조를 정의
        String testString;
    }
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TempExceptionDTO{
        Integer flag;
    }

}
