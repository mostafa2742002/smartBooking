package com.smartbooking.smart_booking.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.smartbooking.smart_booking.dto.ServiceItemRequest;
import com.smartbooking.smart_booking.dto.ServiceItemResponse;
import com.smartbooking.smart_booking.entity.ServiceItem;

@Mapper(componentModel = "spring")
public interface ServiceItemMapper {
    @Mapping(target = "id", ignore = true)
    ServiceItem toEntity(ServiceItemRequest dto);

    ServiceItemResponse toDto(ServiceItem entity);
}