    package by.mycompany.restapilibrary.repository;

    import by.mycompany.restapilibrary.model.Books;
    import org.springframework.data.jpa.repository.JpaRepository;

    import java.util.List;

    public interface BooksRepository extends JpaRepository<Books, Long> {
        List<Books> findByTitleContaining(String title);
        long count();
    }
