package com.vulenhtho.controller;

import com.vulenhtho.model.request.RoleRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class HomeController {

    private RestTemplate restTemplate;

    public HomeController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @GetMapping("/admin/home")
    public ModelAndView home() {
        ModelAndView mav = new ModelAndView("home");
        return mav;
    }

    @GetMapping("/web")
    public ModelAndView web() {
        ModelAndView mav = new ModelAndView("web");

        return mav;
    }

    @GetMapping("/login")
    public ModelAndView login() {
        return new ModelAndView("login");
    }


    @GetMapping("/newUser")
    public ModelAndView addUser(){
        ModelAndView modelAndView = new ModelAndView("user-updateOrCreate");

        List<RoleRequest> roleRequests = restTemplate.getForObject("http://localhost:8888/roles", List.class);
        modelAndView.addObject("roles", roleRequests);

        return modelAndView;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
        }

        return "redirect:/login";
    }

//    @GetMapping(value = "/accessDenied")
//    public String accessDenied(ModelMap model) {
//        model.addAttribute("message", "your aren't permission this url");
//
//        return "redirect:/login";
//    }
}
