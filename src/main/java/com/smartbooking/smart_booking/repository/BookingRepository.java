package com.smartbooking.smart_booking.repository;



import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbooking.smart_booking.entity.Booking;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserEmail(String email);
}