package com.smartbooking.smart_booking.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.smartbooking.smart_booking.dto.RegisterRequest;
import com.smartbooking.smart_booking.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "roles", ignore = true)
    User toEntity(RegisterRequest dto);
}


/**
 * UserMapper Interface
 * 
 * This interface is a MapStruct mapper used to map between the `RegisterRequest` DTO and the `User` entity.
 * It simplifies the conversion process by automatically generating the implementation at compile time.
 * 
 * Annotations:
 * - @Mapper: Marks this interface as a MapStruct mapper.
 *   - `componentModel = "spring"`: Indicates that the generated implementation will be a Spring-managed bean, 
 *     allowing it to be injected into other components using Spring's dependency injection.
 * - @Mapping: Specifies custom mapping rules for specific fields.
 * 
 * Methods:
 * 
 * 1. toEntity(RegisterRequest dto):
 *    - Converts a `RegisterRequest` object into a `User` entity.
 *    - Custom Mapping Rules:
 *      - `id`: Ignored during the mapping process (not set in the resulting `User` object).
 *      - `roles`: Ignored during the mapping process (not set in the resulting `User` object).
 *    - Parameters:
 *      - `dto`: The `RegisterRequest` object to be converted.
 *    - Returns:
 *      - A `User` entity with the mapped fields from the `RegisterRequest` object.
 * 
 * Usage:
 * - This mapper is typically used in service or controller layers to convert incoming DTOs into entities 
 *   before persisting them to the database.
 * - The `id` and `roles` fields are ignored because they are likely managed elsewhere in the application 
 *   (e.g., auto-generated IDs or roles assigned by the system).
 * 
 * Notes:
 * - Ensure that the `RegisterRequest` and `User` classes have compatible field names and types for automatic mapping.
 * - Additional mappings can be added as needed to handle more complex conversion logic.
 */