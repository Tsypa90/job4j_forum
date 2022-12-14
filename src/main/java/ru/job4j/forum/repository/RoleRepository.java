package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Integer> {
}
