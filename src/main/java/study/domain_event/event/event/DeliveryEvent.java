package study.domain_event.event.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import study.domain_event.event.domain.Order;

@NoArgsConstructor
public class DeliveryEvent extends DomainEvent{

    public DeliveryEvent(Order order) {
        super(order);
    }

    public Order getOrder() {
        return (Order) super.getObject();
    }

}
