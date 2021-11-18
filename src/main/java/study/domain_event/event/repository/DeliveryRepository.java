package study.domain_event.event.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import study.domain_event.event.domain.Delivery;
import study.domain_event.event.domain.Order;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    Delivery findByOrder(Order order);

}
