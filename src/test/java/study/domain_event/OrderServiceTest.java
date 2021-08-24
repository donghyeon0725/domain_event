package study.domain_event;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import study.domain_event.event.domain.Delivery;
import study.domain_event.event.domain.Order;
import study.domain_event.event.repository.DeliveryRepository;
import study.domain_event.event.service.OrderService;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    private OrderService orderService;

    @Autowired
    private EntityManager em;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Test
    @DisplayName("상품 주문 테스트")
    void test1() {

        Order order = orderService.createOrder();

        Delivery findDelivery = deliveryRepository.findByOrder(order);

        assertNotNull(findDelivery);
    }

    @Test
    @DisplayName("상품 주문 테스트")
    void test2() {

        Order order = orderService.createOrderAndEvent();

        Delivery findDelivery = deliveryRepository.findByOrder(order);

        assertNotNull(findDelivery);
    }

    @Test
    @DisplayName("상품 주문 테스트")
    void test3() {
        Order order = orderService.createOrderWithRegister();

        Delivery findDelivery = deliveryRepository.findByOrder(order);


        assertNotNull(findDelivery);
    }

    @Test
    @DisplayName("상품 주문 테스트")
    void test4() {
        Order order = orderService.createOrderWithEvents();

        Delivery findDelivery = deliveryRepository.findByOrder(order);

        assertNotNull(findDelivery);
    }


    @Test
    @DisplayName("상품 주문 테스트")
    void test5() {
        Order order = orderService.createOrderWithEventsAndQueue();
        Delivery findDelivery = deliveryRepository.findByOrder(order);

        assertNotNull(findDelivery);
    }





}
