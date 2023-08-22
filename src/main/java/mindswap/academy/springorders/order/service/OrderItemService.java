package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;

import java.util.List;

public interface OrderItemService {
    List<OrderItemDto> getListOfOrderItem(Long orderId);

    OrderDto addItemToOrder(Long orderId, OrderItemDto orderItemAddDto);

    OrderDto updateItemOnOrder(Long orderId, Long itemId, OrderItemUpdateDto OrderItemUpdateDto);

    void removeItemFromOrder(Long orderId, Long itemId);
}
