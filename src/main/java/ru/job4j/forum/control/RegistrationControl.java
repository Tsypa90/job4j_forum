package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.UserService;

@Controller
public class RegistrationControl {
    private final UserService service;

    public RegistrationControl(UserService service) {
        this.service = service;
    }

    @GetMapping("/reg")
    public String reg(Model model) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.equals("anonymousUser")) {
            model.addAttribute("user", User.of("Guest"));
        } else {
            model.addAttribute("user", user);
        }
        return "reg";
    }

    @PostMapping("/reg")
    public String reg(@ModelAttribute("userForm") User user, Model model) {
        boolean rsl = service.save(user);
        if (!rsl) {
            model.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "reg";
        }
        return "redirect:/login";
    }
}
