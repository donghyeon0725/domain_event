package study.domain_event.event.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.domain_event.event.domain.Order;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class DeliveryEvent extends DomainEvent{
    private Order order;
}
