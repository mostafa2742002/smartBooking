package com.smartbooking.smart_booking.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class ServiceItemRequest {

    @NotBlank
    private String name;

    private String description;

    @NotNull
    @Positive
    private Double price;
    
}
