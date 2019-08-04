package com.vulenhtho.service.impl;

import com.vulenhtho.entity.Role;
import com.vulenhtho.entity.User;
import com.vulenhtho.model.request.UserRequest;
import com.vulenhtho.repository.RoleRepository;
import com.vulenhtho.repository.UserRepository;
import com.vulenhtho.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void insert(UserRequest userRequest) {
        final User user = new User();
        BeanUtils.copyProperties(userRequest,user);
        Set<Role> roles = new HashSet<Role>();
        for (Long id : userRequest.getIds()) {
            Role role = roleRepository.findOne(id);
            roles.add(role);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }
}
