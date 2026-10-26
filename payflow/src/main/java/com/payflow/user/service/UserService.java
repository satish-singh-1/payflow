
package com.payflow.user.service;

import com.payflow.user.dto.UserRegistrationRequest;
import com.payflow.user.dto.UserResponse;

public interface UserService {

    UserResponse registerUser(UserRegistrationRequest request);
}
