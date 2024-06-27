package umc.spring.web.dto;

import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class StoreDTO{
        String storeName;
        String storeAddress;

    }
}
