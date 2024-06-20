package umc.spring.apiPayload.exception;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import umc.spring.apiPayload.ApiResponse;
import umc.spring.apiPayload.code.ErrorReasonDTO;
import umc.spring.apiPayload.code.status.ErrorStatus;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;


/**
 * Spring 에서 제공하는 ResponseEntityExceptionHandler의 경우 많은 Exception들을 처리해 주지만, 응답 형태가 자세하지 못하고,
 * Custom Exception의 경우 처리하지 못한다.
 *
 * 그렇기 때문에, ControllerAdvice 클래스에서 해당 클래스에서 제공하는 ExceptionHandler들을 Override 하거나,
 * 새로운 ExceptionHandler를 생성하여 조금 더 원하는 형태로 예외처리를 수행할 수 있다.
 *
 * @RestControllerAdvice : 클래스가 전역 컨트롤러 예외 핸들러로 사용되게 한다. 애플리케이션 전체에서 발생하는 예외에 대한 처리를 담당하게 될 것임.
 */
@Slf4j
@RestControllerAdvice(annotations = {RestController.class}) //전역 예외 처리기로 설정. 애플리케이션 전체에서 발생하는 예외를 처리한다. "RestController" 어노테이션이 있는 클래스에 대해서만 동작한다.
public class ExceptionAdvice extends ResponseEntityExceptionHandler {

    /**
     * @org.springframework.web.bind.annotation.ExceptionHandler 를 사용하면
     * ConstraintVioleationException 예외를 처리할 메서드임을 나타낸다.
     * 이 어노테이션 때문에 ContraintViolcationException이 발생했을 때 valudation 메소드를 호출한다.
     */
    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> validation(ConstraintViolationException e, WebRequest request) {
        // 첫번째 메세지를 추출한다. 예외 발생시 , RunTimeException을 던짐
        String errorMessage = e.getConstraintViolations().stream()
                .map(constraintViolation -> constraintViolation.getMessage())
                .findFirst()
                .orElseThrow(() -> new RuntimeException("ConstraintViolationException 추출 도중 에러 발생"));
        //hadleExceptionInternalConstraint 메서드를 호출해서 RespneseEntity 응답 객체를 생성하고 반환
        return handleExceptionInternalConstraint(e, ErrorStatus.valueOf(errorMessage), HttpHeaders.EMPTY,request);
    }

    /**
     * handleMethodArgumentNotValid 메서드는 ResponseEntityExceptionHandler 클래스에서 제공하는 메서드이다.
     * 주로 MethodArgumentNotValidException 예외를 처리한다. 이 예외는 주로 폼 데이터 유요성 검사시 발생하며, 데이터 바인딩이나
     * 유효성 검사에 실패했을때 호츌된다.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        //필드 에터들을 추출하고 에러 메세지를 맵에 저장한다.
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().stream()
                .forEach(fieldError -> {
                    String fieldNAme = fieldError.getField();
                    String errorMessage = Optional.ofNullable(fieldError.getDefaultMessage()).orElse("");
                    errors.merge(fieldNAme, errorMessage, (existingErrorMessage, newErrorMessage) -> existingErrorMessage + ", " + newErrorMessage);
                });
        return handleExceptionInternalArgs(ex, HttpHeaders.EMPTY, ErrorStatus.valueOf("_BAD_REQUEST"), request, errors);

    }


    /**
     * 마찬가지로 @org.springframework.web.bind.annotation.ExceptionHandler 를 사용하고 있다.
     * Exception 예외를 처리할 메서드임을 나타낸다.
     * 이 어노테이션 때문에 Exception 발생했을 때 exception 메소드를 호출한다.
     */

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> exception(Exception e, WebRequest request) {
        e.printStackTrace();
        //예외 발생시, 스택트레이스를 출력하고 handleExceptionInternalFalse 메서드를 호출해서 ResponseEntity 객체를 생성하고 반환
        return handleExceptionInternalFalse(e, ErrorStatus._INTERNAL_SERVER_ERROR, HttpHeaders.EMPTY, ErrorStatus._INTERNAL_SERVER_ERROR.getHttpStatus(),request, e.getMessage());
    }

    /**
     * @ExceptionHandler : 특정 예외를 처리할 메서드를 지정한다.
     * value 속성을 사용해서 처리할 예외 유형을 명시한다. 여기서는GeneralException 예외를 처리한다.
     * 특정 컨트롤러 내에서 발생하는 특정 예외를 처리하는데 사용. 지정된 컨트롤러에서만 유효하다.
     * @org.springframework.web.bind.annotation.ExceptionHandler는 전역 적인 예외 처리기를 설정하여
     * 애플리케이션 전체에서 발생하는 예외를 처리하는 데 사용된다.
     */

    @ExceptionHandler(value = GeneralException.class)
    public ResponseEntity onThrowException(GeneralException generalException, HttpServletRequest request) {
        ErrorReasonDTO errorReasonHttpStatus = generalException.getErrorReasonHttpStatus(); //ErrorReasonDTO 객체를 가져온다.
        return handleExceptionInternal(generalException,errorReasonHttpStatus,null,request); //다른 메서드들과는 다르게 super가 아닌 커스터마이징된 handleExceptionInternal을 호출
    }




    //내부 핸들러 메서드
    //ResponseEntityExceptionHandler 의 클래스의 handleExceptionInternal 메서드를 호출해서 ResponseEntity 생성
    //그런데 여기서 이 메서드는 handleExceptionInternal를 재정의한 것이며,커스터 마이징 한걸 볼 수 있다.
    // ErrorResonDTO 객체를 바탕으로 ApiResponse 객체를 생성하고, HttpServletRequest객체를 webRequest 객체로 변환하는 과정이 있음.
    // 그리고 부모 클래스의 habdleExceptionInternal 메서드를 호출해서 ResponseEntity를 생성하고 반환하고 있다.
    private ResponseEntity<Object> handleExceptionInternal(Exception e, ErrorReasonDTO reason, HttpHeaders headers, HttpServletRequest request) {

        ApiResponse<Object> body = ApiResponse.onFailure(reason.getCode(),reason.getMessage(),null);
//        e.printStackTrace();

        WebRequest webRequest = new ServletWebRequest(request);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                reason.getHttpStatus(),
                webRequest
        );
    }

    /**
     * errorCommonStatus랑 errorPoint 사용해서 ApiResponse 객체를 생성한다. 그리고
     * ResponseEntityExceptionHandler의 handleExceptionInternal 메서드를 호출하여 ResponseEntity 객체를 생성하고 반환한다.
     */
    private ResponseEntity<Object> handleExceptionInternalFalse(Exception e, ErrorStatus errorCommonStatus,
                                                                HttpHeaders headers, HttpStatus status, WebRequest request, String errorPoint) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorPoint);
        return super.handleExceptionInternal(
                e,
                body,
                headers,
                status,
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalArgs(Exception e, HttpHeaders headers, ErrorStatus errorCommonStatus, WebRequest request, Map<String, String> errorArgs) {
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(),errorCommonStatus.getMessage(),errorArgs);

        return super.handleExceptionInternal(
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }

    private ResponseEntity<Object> handleExceptionInternalConstraint(Exception e, ErrorStatus errorCommonStatus,
                                                                     HttpHeaders headers, WebRequest request) {
        //ErrorStatus 정보를 바탕으로 ApiResponse 객체 생성
        //ApiResponse.onFailure 메서드를 사용해서 실패 응답 객체 생성.
        ApiResponse<Object> body = ApiResponse.onFailure(errorCommonStatus.getCode(), errorCommonStatus.getMessage(), null);
        //ResponseEntityExceptionHandler 의 클래스의 handleExceptionInternal 메서드를 호출해서 ResponseEntity 생성
        return super.handleExceptionInternal( //여러 종류의 예외 처리 메서드가 최종적으로 호출하는 내부 처리 메서드
                // 이 메서드는 예외 발생시 , 적절한 responseEntity 객체를 생성해서 클라이언트에게 반환한다.
                e,
                body,
                headers,
                errorCommonStatus.getHttpStatus(),
                request
        );
    }
}