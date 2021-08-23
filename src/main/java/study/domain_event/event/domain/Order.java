package study.domain_event.event.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.AbstractAggregateRoot;
import study.domain_event.event.aop.Events;
import study.domain_event.event.event.DeliveryEvent;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order extends AbstractAggregateRoot<Order>  {
    @Id @GeneratedValue
    private Long id;

    private String name;

    public void startDelivery() {
        registerEvent(new DeliveryEvent(this));
    }

    public void startDel() {
        Events.raise(new DeliveryEvent(this));
    }

}
