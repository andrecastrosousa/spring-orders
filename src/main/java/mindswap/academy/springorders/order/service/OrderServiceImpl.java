package mindswap.academy.springorders.order.service;

import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Override
    public List<OrderDto> listAll(String email) {
        return null;
    }

    @Override
    public OrderDto findById(String email, Long orderId) {
        return null;
    }

    @Override
    public OrderDto create(String email, OrderCreateDto order) {
        return null;
    }

    @Override
    public void delete(String email, Long orderId) {

    }
}
