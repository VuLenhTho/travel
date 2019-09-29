package com.vulenhtho.controller;


import com.vulenhtho.model.request.UserRequest;
import com.vulenhtho.model.request.UserRequestApi;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class UserController {

    private RestTemplate restTemplate;
    private BCryptPasswordEncoder encoder;

    public UserController(RestTemplate restTemplate, BCryptPasswordEncoder encoder) {
        this.restTemplate = restTemplate;
        this.encoder = encoder;
    }

    @GetMapping("/users")
    public ModelAndView getAllUser(){
        ModelAndView mav = new ModelAndView("user-table");

        List<UserRequestApi> users = restTemplate.getForObject("http://localhost:8888/users", List.class);
        mav.addObject("users", users);

        return mav;
    }

    @GetMapping("/user/{id}")
    public ModelAndView update(@PathVariable Long id){
        ModelAndView mav = new ModelAndView("user-updateOrCreate");

//        mav.addObject("user", userService.findUserById(id));

        return mav;
    }

    @PostMapping("/user")
    public void create(@ModelAttribute UserRequest userRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        userRequest.setPassword(encoder.encode(userRequest.getPassword()));


        HttpEntity request = new HttpEntity(userRequest,headers);

        restTemplate.exchange( "http://localhost:8888/user", HttpMethod.POST, request, Void.class);
    }

}
