package ru.job4j.forum.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.forum.model.Message;

import java.util.List;

public interface MessageRepository extends CrudRepository<Message, Integer> {
    @Query("select m from Message m where m.post.id = :id")
    List<Message> findById(@Param("id") int id);
}
