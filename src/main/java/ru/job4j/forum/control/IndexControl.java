package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;

@Controller
public class IndexControl {
    private final PostService service;

    public IndexControl(PostService service) {
        this.service = service;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.equals("anonymousUser")) {
            model.addAttribute("user", User.of("Guest"));
        } else {
            model.addAttribute("user", user);
        }
        model.addAttribute("posts", service.getAll());
        return "index";
    }
}
