package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Books;

public interface BooksRepository extends JpaRepository<Books, Long> {
}
