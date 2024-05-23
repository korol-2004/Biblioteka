package by.mycompany.restapilibrary.repository;

import by.mycompany.restapilibrary.model.UsersBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersBooksRepository extends JpaRepository<UsersBooks, Long> {

    List<UsersBooks> findByUserId(Long userId);

    List<UsersBooks> findByUserIdAndBookId(Long userId, Long bookId);
    // Здесь можно добавить методы для поиска, если они вам нужны
}
