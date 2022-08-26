package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {
    private final Map<Integer, Post> posts = new HashMap<>();
    private final AtomicInteger ids = new AtomicInteger(1);

    public void savePost(Post post) {
        post.setId(ids.getAndIncrement());
        post.setCreated(LocalDateTime.now());
        posts.put(post.getId(), post);
    }

    public List<Post> getAll() {
        return new ArrayList<>(posts.values());
    }

    public Post findById(int id) {
        return posts.get(id);
    }

    public void edit(Post post) {
        posts.replace(post.getId(), post);
    }

    public void delete(int id) {
        posts.remove(id);
        ids.decrementAndGet();
    }
}
