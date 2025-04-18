package com.smartbooking.smart_booking.service.inter;

import com.smartbooking.smart_booking.dto.RegisterRequest;
import com.smartbooking.smart_booking.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}