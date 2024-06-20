package umc.spring.apiPayload.code;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Builder
@Getter
public class ErrorReasonDTO {
    private HttpStatus httpStatus;
    private String message;
    private String code;
    private Boolean isSuccess;
}
