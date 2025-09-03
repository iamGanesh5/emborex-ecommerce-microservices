package com.ecommerce.user.service;



import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<UserResponse> getAllUsers();
    Optional<UserResponse> getUserById(Long id);


    void createUser(UserRequest userRequest);

    boolean updateUser(Long id, UserRequest updatedUserRequest);
}
