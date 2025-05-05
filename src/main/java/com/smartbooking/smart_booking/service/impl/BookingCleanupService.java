package com.smartbooking.smart_booking.service.impl;

import java.time.LocalDateTime;

import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbooking.smart_booking.repository.BookingRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingCleanupService {

    private final BookingRepository bookingRepo;

    // تشتغل كل يوم في منتصف الليل
    @Scheduled(cron = "0 0 0 * * *")
    @Transactional
    public void deletePastBookings() {
        bookingRepo.deleteAllByBookingTimeBefore(LocalDateTime.now().minusDays(1));
    }
}