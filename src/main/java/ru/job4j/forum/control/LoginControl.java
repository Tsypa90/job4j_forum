package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.UserService;

@Controller
public class LoginControl {
    private final UserService service;

    public LoginControl(UserService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }
}
