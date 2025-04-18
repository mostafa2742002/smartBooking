package com.smartbooking.smart_booking.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbooking.smart_booking.entity.User;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findByUsername(String username);
}
