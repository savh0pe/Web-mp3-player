package com.neloarcher.browserplayer.controller;

import com.neloarcher.browserplayer.domain.User;
import com.neloarcher.browserplayer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public String registerUser() { return "registration"; }

    @PostMapping("/registration")
    public String registerUser(@Valid User user, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            Map<String,String> map=ControllerUtils.getErrors(bindingResult);
            model.mergeAttributes(map);
            return "registration";
        }

        if(!userService.addUser(user)){
            model.addAttribute("message","User already exists");
            return "registration";
        }
        return "redirect:/";
    }
}