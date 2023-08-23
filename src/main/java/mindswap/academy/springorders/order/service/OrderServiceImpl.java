package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.item.dto.ItemDto;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;


    @Autowired
    OrderConverter orderConverter;

    @Override
    public List<OrderDto> listAll(String email) {
        List<OrderDto> orderDtoList = new ArrayList<>();
        orderRepository.findAll().forEach(order -> orderDtoList.add(orderConverter.toDto(order)));
        return orderDtoList;
    }

    @Override
    public OrderDto findById(String email, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        return orderConverter.toDto(order);
    }

    @Override
    public OrderDto create(String email, OrderCreateDto orderCreateDto) {
        Order order = orderConverter.toEntityFromCreateDto(orderCreateDto);
        orderRepository.save(order);

        return orderConverter.toDto(order);
    }

    @Override
    public void delete(String email, Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        orderRepository.delete(order);
    }
}
