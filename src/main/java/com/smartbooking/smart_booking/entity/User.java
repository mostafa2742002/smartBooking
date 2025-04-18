package com.smartbooking.smart_booking.entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.*;

@Entity
@Table(name = "users")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private String fullName;

    @ElementCollection(fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    public enum Role{
        USER, ADMIN
    }
}


/**
 * User Entity Class
 * 
 * This class represents the `User` entity in the application. It is mapped to the `users` table in the database.
 * The class uses JPA annotations for ORM (Object-Relational Mapping) and Lombok annotations for boilerplate code reduction.
 * 
 * Annotations:
 * - @Entity: Marks this class as a JPA entity.
 * - @Table(name = "users"): Specifies the table name in the database.
 * - @Getter, @Setter: Lombok annotations to generate getter and setter methods for all fields.
 * - @NoArgsConstructor, @AllArgsConstructor: Lombok annotations to generate constructors with no arguments and all arguments.
 * - @Builder: Lombok annotation to enable the builder pattern for object creation.
 * 
 * Fields:
 * - id (Long): The primary key of the `users` table. It is auto-generated using the `GenerationType.IDENTITY` strategy.
 * - username (String): The username of the user. It is unique and cannot be null.
 * - password (String): The password of the user. It cannot be null.
 * - fullName (String): The full name of the user. This field is optional.
 * - roles (Set<Role>): A collection of roles assigned to the user. It is an `ElementCollection` with eager fetching and uses the `EnumType.STRING` for storing enum values in the database.
 * 
 * Enum:
 * - Role: Defines the roles a user can have. Possible values are:
 *   - USER: Regular user role.
 *   - ADMIN: Administrator role.
 * 
 * Notes:
 * - The `roles` field is stored as a collection of strings in the database, corresponding to the `Role` enum values.
 * - The use of Lombok annotations reduces boilerplate code, making the class more concise and readable.
 */