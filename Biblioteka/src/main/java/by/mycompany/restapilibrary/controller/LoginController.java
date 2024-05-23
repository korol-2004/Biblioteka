package by.mycompany.restapilibrary.controller;

import by.mycompany.restapilibrary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
class LoginController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    String login() {
        return "login";
    }
}
