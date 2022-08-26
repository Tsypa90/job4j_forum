package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;

@Controller
public class PostControl {
    private final PostService service;

    public PostControl(PostService service) {
        this.service = service;
    }

    @GetMapping("/add")
    public String add() {
        return "add";
    }

    @GetMapping("/posts/{postId}")
    public String post(Model model, @PathVariable("postId") int id) {
        model.addAttribute("post", service.findById(id));
        return "post";
    }

    @GetMapping("/update/{postId}")
    public String edit(@PathVariable("postId") int id, Model model) {
        model.addAttribute("post", service.findById(id));
        return "edit";
    }
    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable("postId") int id) {
        service.delete(id);
        return "redirect:/index";
    }

    @PostMapping("/add")
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
