package com.vulenhtho.service;

import com.vulenhtho.entity.Role;

import java.util.List;

public interface RoleService {
    Role findOne(long id);

    List<Role> findAll();
}
