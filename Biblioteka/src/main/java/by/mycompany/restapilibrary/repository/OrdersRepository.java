package by.mycompany.restapilibrary.repository;

import by.mycompany.restapilibrary.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
    List<Orders> findByUserId(Long userId);
    long count();

    List<Orders> findByBookId(Long bookId);
}
