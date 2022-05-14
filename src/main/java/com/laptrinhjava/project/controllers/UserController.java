package com.laptrinhjava.project.controllers;

import com.laptrinhjava.project.entities.User;
import com.laptrinhjava.project.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import javax.validation.Valid;

@Controller
public class UserController {
    @Autowired
    private IUserService userService ;
    @Autowired
    private PasswordEncoder bCryptEncoder;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user",new User());
        return "register";
    }
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute User user, BindingResult result,Model model){
        if (result.hasErrors()
                || userService.findOneByUsername(user.getUserName()) != null
                || user.getPasswordMatch()== null
                || !user.getPassword().equals(user.getPasswordMatch())
                || (user.getPassword().length()<4 || user.getPassword().length()>20)

        ) {
            if (userService.findOneByUsername(user.getUserName()) != null) {
                model.addAttribute("error", "Username has already been taken");
            }
            if (user.getPasswordMatch() == null) {
                model.addAttribute("error0", "You must confirm your password");
            }
            if (!user.getPassword().equals(user.getPasswordMatch()))
                model.addAttribute("error1", "Password does not match");
            if(user.getPassword().length()<4 || user.getPassword().length()>20)
                model.addAttribute("error2","Password must be between 4 and 20 characters");
            model.addAttribute("user",user);
            return "register";
        }
        user.setPassword(bCryptEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        try {
            userService.save(user);
            return "login";
        }
        catch (Exception e){
            System.out.println(e.getMessage());
            return "register";
        }


    }
}
