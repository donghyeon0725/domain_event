package study.domain_event.event.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import study.domain_event.event.aop.Events;
import study.domain_event.event.domain.Delivery;
import study.domain_event.event.domain.Order;
import study.domain_event.event.event.DeliveryEvent;
import study.domain_event.event.repository.DeliveryRepository;
import study.domain_event.event.repository.OrderRepository;

import java.util.Arrays;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final DeliveryRepository deliveryRepository;

    private final OrderRepository orderRepository;


    @Transactional
    public Order createOrder() {
        Order order = Order.builder().name("주문").build();
        orderRepository.save(order);

        Delivery delivery = Delivery.builder().order(order).build();
        deliveryRepository.save(delivery);

        return order;
    }

    private final ApplicationEventPublisher applicationEventPublisher;

    @Transactional
    public Order createOrderAndEvent() {
        Order order = Order.builder().name("주문").build();
        orderRepository.save(order);

        applicationEventPublisher.publishEvent(new DeliveryEvent(order));

        return order;
    }

    @Transactional
    public Order createOrderWithRegister() {
        Order order = Order.builder().name("주문").build();
        order.startDelivery();
        orderRepository.save(order);

        return order;
    }

    @Transactional
    public Order createOrderWithEvents() {
        Order order = Order.builder().name("주문").build();
        orderRepository.save(order);

        order.startDel();

        return order;
    }

    @Transactional
    public Order createOrderWithEventsAndQueue() {
        Order order = Order.builder().name("주문").build();
        orderRepository.save(order);

        order.startDel();

        Order newOrder = Order.builder().name("주문").build();
        Events.raise(new DeliveryEvent(newOrder));

        orderRepository.save(newOrder);

        return order;
    }

}
