package com.greetlabs.swiftcart.controller;

import com.greetlabs.swiftcart.dto.LoginDto;
import com.greetlabs.swiftcart.dto.UserDto;
import com.greetlabs.swiftcart.service.LoginService;
import com.greetlabs.swiftcart.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;


    @PostMapping("/register")
    public String userRegister(@RequestBody UserDto userDto) {
        return userService.createUser(userDto);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return loginService.userLoginVerification(loginDto);
    }

    @GetMapping("/greet")
    public String greet() {
        return "Hello, User!";
    }
}
