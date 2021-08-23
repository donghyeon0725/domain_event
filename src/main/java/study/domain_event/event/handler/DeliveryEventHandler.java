package study.domain_event.event.handler;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import study.domain_event.event.domain.Delivery;
import study.domain_event.event.domain.Order;
import study.domain_event.event.event.DeliveryEvent;
import study.domain_event.event.repository.DeliveryRepository;

@Component
@RequiredArgsConstructor
public class DeliveryEventHandler {

    private final DeliveryRepository deliveryRepository;

    @Async
    @Transactional
    @EventListener
    public void handle(DeliveryEvent event) {
        Order order = event.getOrder();
        deliveryRepository.save(Delivery.builder().order(order).build());
    }
}
