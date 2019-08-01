package com.vulenhtho.service;

import com.vulenhtho.model.request.UserRequest;
import org.springframework.stereotype.Service;


public interface UserService {
    void insert(UserRequest userRequest);

}
