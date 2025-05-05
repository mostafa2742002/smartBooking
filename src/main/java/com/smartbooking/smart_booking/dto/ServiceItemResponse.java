package com.smartbooking.smart_booking.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceItemResponse {
    private Long id;
    private String name;
    private String description;
    private Double price;
}