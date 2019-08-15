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

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        Set<Role> roles = new HashSet<Role>();
        for (Long id : userRequest.getIds()) {
            Role role = roleRepository.findOne(id);
            roles.add(role);
        }
        user.setRoles(roles);
        user.setCreatedDate(new Date());
        userRepository.save(user);

    }

    @Override
    public void update(UserRequest userRequest) {
        User user = new User();
        BeanUtils.copyProperties(userRequest, user);
        user.setUserName(userRepository.findUserNameById(userRequest.getId()).getUserName());
        user.setCreatedDate(userRepository.findUserNameById(userRequest.getId()).getCreatedDate());
        Set<Role> roles = new HashSet<>();
        for (Long id : userRequest.getIds()) {
            Role role = roleRepository.findOne(id);
            roles.add(role);
        }
        user.setRoles(roles);
        user.setModifiedDate(new Date());
        List<UserRepository.CustomObjectResponse> customObjectResponse = userRepository.findAllByProperties(null,new Date(), "admin1");
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User user = userRepository.findOne(id);
        for (Role role : user.getRoles()) {
            role.getUsers().remove(user);
        }
        userRepository.delete(id);
    }

    @Override
    public User findUserByUserNameAndPassword(String userName, String password) {
        return userRepository.findUserByUserNameAndPassword(userName,password);
    }
}
