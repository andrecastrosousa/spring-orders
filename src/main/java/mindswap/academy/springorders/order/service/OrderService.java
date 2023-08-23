package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> listAll();

    OrderDto findById(Long orderId);

    OrderDto create(OrderCreateDto order);

    void delete(Long orderId);
}
