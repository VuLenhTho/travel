package com.vulenhtho.controller;

import com.vulenhtho.entity.Role;
import com.vulenhtho.model.request.UserRequest;
import com.vulenhtho.service.UserService;
import com.vulenhtho.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
//    private UserServiceImpl userService;
//
//    @Autowired
//    public HomeController(UserServiceImpl userService) {
//        this.userService = userService;
//    }

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public ModelAndView homePage(){
        ModelAndView mav = new ModelAndView("home");
        mav.addObject("mess","TEST message");
//        UserRequest userRequest = new UserRequest();
//        userRequest.setPassword("1230");
//        userRequest.setUserName("tho");
//        List<Long> roles = new ArrayList<Long>();
//        roles.add(1L);
//        roles.add(2L);
//        userService.insert(userRequest);
        return mav;
    }
}
