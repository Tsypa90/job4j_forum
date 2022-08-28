package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    @EntityGraph(attributePaths = "messages")
    List<Post> findByOrderByIdAsc();

    @EntityGraph(attributePaths = "messages")
    Post findById(int id);
}
