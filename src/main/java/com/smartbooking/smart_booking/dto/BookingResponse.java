package com.smartbooking.smart_booking.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class BookingResponse {
    private Long id;
    private String email;
    private Long serviceId;
    private String serviceName;
    private LocalDateTime bookingTime;
    private LocalDateTime createdAt;
}