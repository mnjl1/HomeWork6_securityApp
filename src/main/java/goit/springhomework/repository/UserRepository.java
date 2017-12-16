package goit.springhomework.repository;

import goit.springhomework.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findBy(String username);
}
