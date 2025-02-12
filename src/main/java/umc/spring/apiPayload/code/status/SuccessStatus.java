package umc.spring.apiPayload.code.status;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;


@Getter
@RequiredArgsConstructor
public enum SuccessStatus {
    _OK(HttpStatus.OK, "200 Request", "올바른 요청입니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

}
