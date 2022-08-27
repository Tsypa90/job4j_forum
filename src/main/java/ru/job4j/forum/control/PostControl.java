package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.forum.model.Message;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.MessageService;
import ru.job4j.forum.service.PostService;

import java.time.LocalDateTime;

@Controller
public class PostControl {
    private final PostService postService;
    private final MessageService messageService;

    public PostControl(PostService postService, MessageService messageService) {
        this.postService = postService;
        this.messageService = messageService;
    }

    private void addUserToModel(Model model) {
        var user = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (user.equals("anonymousUser")) {
            model.addAttribute("user", User.of("Guest"));
        } else {
            model.addAttribute("user", user);
        }
    }

    @GetMapping("/add")
    public String add(Model model) {
        addUserToModel(model);
        return "add";
    }

    @GetMapping("/posts/{postId}")
    public String post(Model model, @PathVariable("postId") int id) {
        addUserToModel(model);
        model.addAttribute("post", postService.findById(id));
        model.addAttribute("msgs", messageService.finaById(id));
        return "post";
    }

    @GetMapping("/update/{postId}")
    public String edit(@PathVariable("postId") int id, Model model) {
        addUserToModel(model);
        model.addAttribute("post", postService.findById(id));
        return "edit";
    }

    @GetMapping("/addmsg/{postId}")
    public String addMsg(@PathVariable("postId") int id, Model model) {
        addUserToModel(model);
        model.addAttribute("post", postService.findById(id));
        return "addmsg";
    }
    @PostMapping("/delete/{postId}")
    public String delete(@PathVariable("postId") int id) {
        postService.delete(id);
        return "redirect:/index";
    }

    @PostMapping("/addmsg/{postId}")
    public String addMsg(@RequestParam("message") String message, @PathVariable("postId") int id) {
        Message msg = Message.of(message);
        Post post = postService.findById(id);
        msg.setPost(post);
        post.addMsg(msg);
        postService.edit(post);
        return "redirect:/posts/{postId}";
    }

    @PostMapping("/add")
    public String savePost(@ModelAttribute Post post) {
        postService.savePost(post);
        return "redirect:/index";
    }

    @PostMapping("/edit")
    public String editPost(@ModelAttribute Post post, @RequestParam("date") String date) {
        post.setCreated(LocalDateTime.parse(date));
        postService.edit(post);
        return "redirect:/index";
    }
}
