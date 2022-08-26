package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserService {
 private final Map<Integer, User> users = new HashMap<>();
 private final AtomicInteger ids = new AtomicInteger(1);

 public User save(User user) {
  user.setId(ids.getAndIncrement());
  return users.put(user.getId(), user);
 }
}

