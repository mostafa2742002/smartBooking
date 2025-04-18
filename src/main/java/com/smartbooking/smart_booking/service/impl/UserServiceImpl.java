package com.smartbooking.smart_booking.service.impl;

import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.smartbooking.smart_booking.dto.RegisterRequest;
import com.smartbooking.smart_booking.entity.User;
import com.smartbooking.smart_booking.exception.ResourceAlreadyExistsException;
import com.smartbooking.smart_booking.mapper.UserMapper;
import com.smartbooking.smart_booking.repository.UserRepository;
import com.smartbooking.smart_booking.service.inter.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User register(RegisterRequest request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new ResourceAlreadyExistsException("Username '" + request.getUsername() + "' is already taken");
        }
        User user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRoles(Set.of(User.Role.USER));
        return userRepository.save(user);
    }

}







/**
 * UserServiceImpl Class
 * 
 * This class is an implementation of the `UserService` interface and provides the business logic 
 * for user-related operations, such as user registration. It uses Spring's dependency injection 
 * to manage its dependencies and is marked as a Spring service.
 * 
 * Annotations:
 * - @Service: Marks this class as a Spring-managed service component.
 * - @RequiredArgsConstructor: Automatically generates a constructor for all final fields, enabling dependency injection.
 * - @Transactional: Ensures that the `register` method is executed within a transactional context.
 * 
 * Dependencies:
 * - `UserRepository`: Used to interact with the database for user-related operations.
 * - `UserMapper`: Converts `RegisterRequest` DTOs into `User` entities.
 * - `PasswordEncoder`: Encodes user passwords securely before saving them to the database.
 * 
 * Methods:
 * 
 * 1. register(RegisterRequest request):
 *    - Registers a new user in the system.
 *    - Steps:
 *      1. Checks if a user with the given username already exists in the database.
 *         - If the username is already taken, throws a `ResourceAlreadyExistsException`.
 *      2. Converts the `RegisterRequest` DTO into a `User` entity using the `UserMapper`.
 *      3. Encodes the user's password using the `PasswordEncoder`.
 *      4. Sets the user's roles to `USER` by default.
 *      5. Saves the user entity to the database using the `UserRepository`.
 *      6. Returns the saved `User` entity.
 *    - Parameters:
 *      - `request`: A `RegisterRequest` object containing the user's registration details (e.g., username, password).
 *    - Returns:
 *      - The saved `User` entity.
 *    - Throws:
 *      - `ResourceAlreadyExistsException` if the username is already taken.
 * 
 * Usage:
 * - This class is used to handle user registration logic in the application.
 * - It ensures that usernames are unique and that passwords are securely encoded before being stored.
 * 
 * Notes:
 * - The `UserMapper` simplifies the conversion of DTOs to entities, reducing boilerplate code.
 * - The `PasswordEncoder` ensures that passwords are stored securely in a hashed format.
 * - The `@Transactional` annotation ensures that the database operations are rolled back in case of an exception.
 */