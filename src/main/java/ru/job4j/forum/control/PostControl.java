package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.service.PostService;

@Controller
public class PostControl {
    private final PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @GetMapping("/post")
    public String post() {
        return "post";
    }

    @GetMapping("/edit")
    public String edit() {
        return "edit";
    }
}
