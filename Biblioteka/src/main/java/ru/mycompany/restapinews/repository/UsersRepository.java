package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Users;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
