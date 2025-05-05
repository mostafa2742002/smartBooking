package com.smartbooking.smart_booking.mapper;
import org.mapstruct.*;

import com.smartbooking.smart_booking.dto.BookingResponse;
import com.smartbooking.smart_booking.entity.Booking;


@Mapper(componentModel = "spring")
public interface BookingMapper {
    @Mapping(source = "user.email", target = "email")
    @Mapping(source = "serviceItem.id",   target = "serviceId")
    @Mapping(source = "serviceItem.name", target = "serviceName")
    BookingResponse toDto(Booking booking);
}