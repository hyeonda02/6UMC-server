package umc.spring.web.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class StoreDTO{
        @NotNull
        String storeName;
        @NotNull
        String storeAddress;
    }

    @Getter
    public static class ReviewDTO{
        @NotNull
        String reviewContent;
        @NotNull
        Float reviewGrade;
    }

}
