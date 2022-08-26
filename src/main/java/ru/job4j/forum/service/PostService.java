package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.repository.PostRepository;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class PostService {
    private final PostRepository store;

    public PostService(PostRepository store) {
        this.store = store;
    }

    public void savePost(Post post) {
        post.setCreated(LocalDateTime.now());
        store.save(post);
    }

    public List<Post> getAll() {
        return store.findByOrderByIdAsc();
    }

    public Post findById(int id) {
        return store.findById(id);
    }

    public void edit(Post post) {
        store.save(post);
    }

    public void delete(int id) {
        Post post = findById(id);
        store.delete(post);
    }
}
