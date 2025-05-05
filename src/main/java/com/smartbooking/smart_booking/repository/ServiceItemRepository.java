package com.smartbooking.smart_booking.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.smartbooking.smart_booking.entity.ServiceItem;

@Repository
public interface ServiceItemRepository extends JpaRepository<ServiceItem,Long>{

    Page<ServiceItem> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
