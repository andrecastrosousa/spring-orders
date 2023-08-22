package mindswap.academy.springorders.order.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderConverter {
    @Autowired
    ObjectMapper objectMapper;

    public Order fromDto(OrderDto orderDto) {
        return objectMapper.convertValue(orderDto, Order.class);
    }

    public OrderDto toDto(Order order) {
        return objectMapper.convertValue(order, OrderDto.class);
    }

    public Order toEntityFromCreateDto(OrderCreateDto orderCreateDto) {
        return objectMapper.convertValue(orderCreateDto, Order.class);
    }
}
