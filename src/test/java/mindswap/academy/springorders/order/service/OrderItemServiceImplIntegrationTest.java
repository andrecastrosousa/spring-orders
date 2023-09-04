package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.converter.OrderItemConverter;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.model.OrderItem;
import mindswap.academy.springorders.order.repository.OrderItemRepository;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceImplIntegrationTest {

    @InjectMocks
    OrderItemServiceImpl orderItemService;

    @Mock
    OrderConverter orderConverter;

    @Mock
    OrderItemConverter orderItemConverter;

    @Mock
    OrderItemRepository orderItemRepository;

    @Mock
    OrderRepository orderRepository;

    @BeforeEach
    public void setup() {
        Order order = new Order();

        Item item = new Item();

        order.setOrderItems(new ArrayList<>(List.of(new OrderItem())));

        Mockito.when(orderRepository.findById(1L)).thenReturn(Optional.of(order));
    }

    @Test
    public void getListOfOrdersAssociatedToAnItem() {
        assertEquals(1, orderItemService.getListOfOrderItem(1L).size());
    }
}
