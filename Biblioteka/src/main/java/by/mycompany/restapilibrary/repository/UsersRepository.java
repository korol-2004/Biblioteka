package by.mycompany.restapilibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import by.mycompany.restapilibrary.model.Users;
import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Long> {
    List<Users> findByEmailContaining(String email);  // Имя метода должно соответствовать имени поля в сущности
    long count();
}
