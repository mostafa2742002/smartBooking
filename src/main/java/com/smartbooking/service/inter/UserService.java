package com.smartbooking.service.inter;

import com.smartbooking.dto.RegisterRequest;
import com.smartbooking.entity.User;

public interface UserService {
    User register(RegisterRequest request);
}