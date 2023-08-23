package mindswap.academy.springorders.order.service;

import jakarta.inject.Inject;
import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.item.repository.ItemRepository;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.converter.OrderItemConverter;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.repository.OrderItemRepository;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderItemServiceImplTest {
    @Inject
    OrderItemServiceImpl orderItemService;

    @InjectMocks
    OrderItemRepository orderItemRepository;
    @InjectMocks
    OrderRepository orderRepository;
    @InjectMocks
    ItemRepository itemRepository;
    @InjectMocks
    OrderConverter orderConverter;
    @InjectMocks
    OrderItemConverter orderItemConverter;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    public void recalculateTotalFromExistentItemIncrementQuantity() {
        Order order = new Order();
        order.setTotal(60);

        Item item = new Item();
        item.setPrice(20);
        item.setName("test");

        order.setTotal(orderItemService.recalculateTotal(order, item, 3, 7));

        assertEquals(140, order.getTotal());
    }

    @Test
    public void recalculateTotalFromExistentItemDecrementQuantity() {
        Order order = new Order();
        order.setTotal(60);

        Item item = new Item();
        item.setPrice(20);
        item.setName("test");

        order.setTotal(orderItemService.recalculateTotal(order, item, 3, 2));

        assertEquals(40, order.getTotal());
    }
}
