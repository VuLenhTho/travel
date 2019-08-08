package com.vulenhtho.service;

import com.vulenhtho.model.request.UserRequest;

import java.util.List;


public interface UserService {
    void insert(UserRequest userRequest);

    void update(UserRequest userRequest);

    void delete(Long id);
}
