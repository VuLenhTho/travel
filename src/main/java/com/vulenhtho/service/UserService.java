package com.vulenhtho.service;

import com.vulenhtho.entity.User;
import com.vulenhtho.model.request.UserRequest;


public interface UserService {
    void insert(UserRequest userRequest);

    void update(UserRequest userRequest);

    void delete(Long id);

    User findUserByUserNameAndPassword(String userName, String password);
}
