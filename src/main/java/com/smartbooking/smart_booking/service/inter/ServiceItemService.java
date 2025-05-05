package com.smartbooking.smart_booking.service.inter;

import com.smartbooking.smart_booking.dto.ServiceItemRequest;
import com.smartbooking.smart_booking.dto.ServiceItemResponse;

import java.util.List;

public interface ServiceItemService {
    ServiceItemResponse create(ServiceItemRequest req);

    List<ServiceItemResponse> listAll();

    void delete(Long id);
}