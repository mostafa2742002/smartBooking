package com.smartbooking.smart_booking.service.inter;


import java.util.List;

import com.smartbooking.smart_booking.dto.BookingRequest;
import com.smartbooking.smart_booking.dto.BookingResponse;

public interface BookingService {
    BookingResponse book(String username, BookingRequest req);
    List<BookingResponse> listByUser(String username);
    void cancel(String username, Long bookingId);
}