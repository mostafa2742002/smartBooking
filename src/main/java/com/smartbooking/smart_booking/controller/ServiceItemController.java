package com.smartbooking.smart_booking.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.smartbooking.smart_booking.dto.ServiceItemRequest;
import com.smartbooking.smart_booking.dto.ServiceItemResponse;
import com.smartbooking.smart_booking.service.inter.ServiceItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("api/services")
@RequiredArgsConstructor
public class ServiceItemController {

    private final ServiceItemService service;

    // فقط ADMIN يقدر ينشئ ويحدف
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<ServiceItemResponse> create(@Valid @RequestBody ServiceItemRequest req) {
        return ResponseEntity.ok(service.create(req));
    }

    // @GetMapping
    // public ResponseEntity<List<ServiceItemResponse>> listAll() {
    //     return ResponseEntity.ok(service.listAll());
    // }
    @GetMapping
    public ResponseEntity<Page<ServiceItemResponse>> listAll(
            @RequestParam(defaultValue = "") String name,
            Pageable pageable) {
        Page<ServiceItemResponse> page = service.list(name, pageable);
        return ResponseEntity.ok(page);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

}