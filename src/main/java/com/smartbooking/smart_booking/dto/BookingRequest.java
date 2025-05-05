package com.smartbooking.smart_booking.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class BookingRequest {
    @NotNull
    private Long serviceId;

    @NotNull
    private LocalDateTime bookingTime;
}