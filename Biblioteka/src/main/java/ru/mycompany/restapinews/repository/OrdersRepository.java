package ru.mycompany.restapinews.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.mycompany.restapinews.model.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
