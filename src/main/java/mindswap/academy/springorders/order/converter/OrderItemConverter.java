package mindswap.academy.springorders.order.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {
    @Autowired
    ObjectMapper objectMapper;

    public OrderItemDto toDto(OrderItem orderItem) {
        return objectMapper.convertValue(orderItem, OrderItemDto.class);
    }
}
