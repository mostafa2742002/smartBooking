package com.smartbooking.smart_booking.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.smartbooking.smart_booking.dto.BookingRequest;
import com.smartbooking.smart_booking.dto.BookingResponse;
import com.smartbooking.smart_booking.entity.Booking;
import com.smartbooking.smart_booking.entity.ServiceItem;
import com.smartbooking.smart_booking.entity.User;
import com.smartbooking.smart_booking.exception.ResourceNotFoundException;
import com.smartbooking.smart_booking.mapper.BookingMapper;
import com.smartbooking.smart_booking.repository.BookingRepository;
import com.smartbooking.smart_booking.repository.ServiceItemRepository;
import com.smartbooking.smart_booking.repository.UserRepository;
import com.smartbooking.smart_booking.service.inter.BookingService;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepo;
    private final UserRepository userRepo;
    private final ServiceItemRepository serviceRepo;
    private final BookingMapper mapper;

    @Override
    @Transactional
    public BookingResponse book(String email, BookingRequest req) {
        User user = userRepo.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User","Email",email));

        ServiceItem item = serviceRepo.findById(req.getServiceId())
            .orElseThrow(() -> new ResourceNotFoundException("Service","ServiceId", req.getServiceId().toString()));

        Booking booking = Booking.builder()
            .user(user)
            .serviceItem(item)
            .bookingTime(req.getBookingTime())
            .createdAt(LocalDateTime.now())
            .build();

        Booking saved = bookingRepo.save(booking);
        return mapper.toDto(saved);
    }

    @Override
    public List<BookingResponse> listByUser(String username) {
        return bookingRepo.findByUserEmail(username)
                          .stream()
                          .map(mapper::toDto)
                          .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void cancel(String username, Long bookingId) {
        Booking booking = bookingRepo.findById(bookingId)
            .orElseThrow(() -> new ResourceNotFoundException("Book", "bookingId",bookingId.toString()));
        if (!booking.getUser().getEmail().equals(username)) {
            throw new IllegalStateException("Cannot cancel someone else's booking");
        }
        bookingRepo.delete(booking);
    }

    
}