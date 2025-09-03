package com.ecommerce.order.dto;


import com.ecommerce.order.dto.AddressDTO;
import com.ecommerce.user.models.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String keyCloakId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole role;
    private AddressDTO address;
}
