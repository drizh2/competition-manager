package edu.katsala.competitionmanager.controller;

import edu.katsala.competitionmanager.payload.request.CreateUserRequest;
import edu.katsala.competitionmanager.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping(
            path = "/register")
    public String signup(CreateUserRequest request) {
        userService.createUser(request);

        return "redirect:/signin";
    }
}
