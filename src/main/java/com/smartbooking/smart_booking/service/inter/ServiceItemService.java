package com.smartbooking.smart_booking.service.inter;

import com.smartbooking.smart_booking.dto.ServiceItemRequest;
import com.smartbooking.smart_booking.dto.ServiceItemResponse;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ServiceItemService {
    ServiceItemResponse create(ServiceItemRequest req);

    List<ServiceItemResponse> listAll();

    void delete(Long id);

    Page<ServiceItemResponse> list(String nameFilter, Pageable pageable);
}