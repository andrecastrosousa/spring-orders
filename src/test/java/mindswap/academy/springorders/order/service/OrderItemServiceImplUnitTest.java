package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.order.model.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class OrderItemServiceImplUnitTest {

    @Mock
    OrderItemServiceImpl orderItemService;

    @Test
    public void recalculateTotalFromExistentItemIncrementQuantity() {
        Order order = new Order();
        order.setTotal(60);

        Item item = new Item();
        item.setPrice(20);
        item.setName("test");

        Mockito.when(orderItemService.recalculateTotal(order, item, 3, 7)).thenReturn(140.0);

        assertEquals(140, orderItemService.recalculateTotal(order, item, 3, 7));
    }

    @Test
    public void recalculateTotalFromExistentItemDecrementQuantity() {
        Order order = new Order();
        order.setTotal(60);

        Item item = new Item();
        item.setPrice(20);
        item.setName("test");

        Mockito.when(orderItemService.recalculateTotal(order, item, 3, 2)).thenReturn(40.0);

        assertEquals(40, orderItemService.recalculateTotal(order, item, 3, 2));
    }
}
