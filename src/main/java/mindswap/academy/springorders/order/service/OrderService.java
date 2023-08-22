package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;

import java.util.List;

public interface OrderService {
    List<OrderDto> listAll(String email);

    OrderDto findById(String email, Long orderId);

    OrderDto create(String email, OrderCreateDto order);

    void delete(String email, Long orderId);
}
