package com.smartbooking.smart_booking.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private ServiceItemRepository serviceItemRepository;
    @Autowired
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
            throw new ResourceNotFoundException("ServiceItem","Id", id.toString());
        }
        serviceItemRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "services", key = "#nameFilter + '-' + #pageable.pageNumber + '-' + #pageable.pageSize")
    public Page<ServiceItemResponse> list(String nameFilter, Pageable pageable) {
        return serviceItemRepository.findByNameContainingIgnoreCase(nameFilter, pageable)
                .map(serviceItemMapper::toDto);
    }

}
