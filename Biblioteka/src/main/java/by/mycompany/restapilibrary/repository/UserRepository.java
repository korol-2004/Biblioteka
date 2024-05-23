package by.mycompany.restapilibrary.repository;

import by.mycompany.restapilibrary.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
