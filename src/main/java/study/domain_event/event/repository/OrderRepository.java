package study.domain_event.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain_event.event.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
