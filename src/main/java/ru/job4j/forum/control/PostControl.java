package ru.job4j.forum.control;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

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
    public String edit(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", service.findById(id));
        return "edit";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        service.delete(id);
        return "delete";
    }

    @PostMapping("/post")
    public String savePost(@ModelAttribute Post post) {
        service.savePost(post);
        return "redirect:/index";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Post post, @RequestParam("date") String date) {
        post.setCreated(LocalDateTime.parse(date));
        service.edit(post);
        return "redirect:/index";
    }
}
