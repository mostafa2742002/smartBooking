package com.smartbooking.smart_booking.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.smartbooking.smart_booking.dto.BookingRequest;
import com.smartbooking.smart_booking.dto.BookingResponse;
import com.smartbooking.smart_booking.service.inter.BookingService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService service;

    // احجز خدمة
    @PostMapping
    public ResponseEntity<BookingResponse> book(
            @AuthenticationPrincipal UserDetails user,
            @Valid @RequestBody BookingRequest req) {
        return ResponseEntity.ok(service.book(user.getUsername(), req));
    }

    // اعرض حجوزاتي
    @GetMapping
    public ResponseEntity<List<BookingResponse>> listMy(
            @AuthenticationPrincipal UserDetails user) {
        return ResponseEntity.ok(service.listByUser(user.getUsername()));
    }

    // الغي حجز
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> cancel(
            @AuthenticationPrincipal UserDetails user,
            @PathVariable Long id) {
        service.cancel(user.getUsername(), id);
        return ResponseEntity.noContent().build();
    }
}