package com.vulenhtho.service.impl;

import com.vulenhtho.entity.Role;
import com.vulenhtho.entity.User;
import com.vulenhtho.repository.UserRepository;
import com.vulenhtho.security.CustomUserDetail;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
public class CustomUserDetailService implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public CustomUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findUserByUserName(s);
        if (Objects.isNull(user)){
            throw new UsernameNotFoundException("Didn't found user by username " + s);
        }

        Set<GrantedAuthority> authoritySet = new HashSet<>();

        for (Role role: user.getRoles()) {
            authoritySet.add(new SimpleGrantedAuthority(role.getName()));
        }

        UserDetails userDetails = new CustomUserDetail(s,user.getPassword(),authoritySet);
        BeanUtils.copyProperties(user,userDetails);
        return userDetails;
    }
}
