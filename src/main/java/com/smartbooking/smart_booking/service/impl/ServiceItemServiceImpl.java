package com.smartbooking.smart_booking.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.smartbooking.smart_booking.dto.ServiceItemRequest;
import com.smartbooking.smart_booking.dto.ServiceItemResponse;
import com.smartbooking.smart_booking.entity.ServiceItem;
import com.smartbooking.smart_booking.exception.ResourceNotFoundException;
import com.smartbooking.smart_booking.mapper.ServiceItemMapper;
import com.smartbooking.smart_booking.repository.ServiceItemRepository;
import com.smartbooking.smart_booking.service.inter.ServiceItemService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ServiceItemServiceImpl implements ServiceItemService{

    private ServiceItemRepository serviceItemRepository;
    private ServiceItemMapper serviceItemMapper;
    
    @Override
    public ServiceItemResponse create(ServiceItemRequest req) {
        
        ServiceItem serviceItem = serviceItemMapper.toEntity(req);
        ServiceItem serviceItemSaved = serviceItemRepository.save(serviceItem);

        return serviceItemMapper.toDto(serviceItemSaved);
    }
    @Override
    public List<ServiceItemResponse> listAll() {
        
        return serviceItemRepository.findAll()
                .stream()
                .map(serviceItemMapper::toDto)
                .toList();
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!serviceItemRepository.existsById(id)) {
            throw new ResourceNotFoundException("ServiceItem with id " + id + " not found");
        }
        serviceItemRepository.deleteById(id);
    }



}
