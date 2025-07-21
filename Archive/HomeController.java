package com.preet.store.controllers;

import com.preet.store.entities.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @RequestMapping("/")
    User index(Model model)
    {
        model.addAttribute("name","Preet");
        User user = new User();
        user.setName("preet");
        user.setEmail("hello@test.mail");
        user.setPassword("pass");
        return user;
    }
}
