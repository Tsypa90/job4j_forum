package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;

import java.util.List;

@Repository
public interface PostRepository extends CrudRepository<Post, Integer> {

    List<Post> findByOrderByIdAsc();

    Post findById(int id);
}
