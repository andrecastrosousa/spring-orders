package mindswap.academy.springorders.order.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;
import mindswap.academy.springorders.order.service.OrderItemService;
import mindswap.academy.springorders.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController(value = "/orders")
public class OrderItemController {
    OrderItemService orderItemService;

    @Autowired
    OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{orderId}/items")
    @RolesAllowed("user")
    public List<OrderItemDto> get(@RequestParam("orderId") Long orderId) {
        return orderItemService.getListOfOrderItem(orderId);
    }

    @PutMapping("/{orderId}/items")
    @Transactional
    public OrderDto addItemToOrder(
            @RequestParam("orderId") Long orderId,
            OrderItemDto orderItemAddDto
    ) {
        return orderItemService.addItemToOrder(orderId, orderItemAddDto);
    }

    @PutMapping("/{orderId}/items/{itemId}")
    @Transactional
    public OrderDto updateItemOnOrder(
            @RequestParam("orderId") Long orderId,
            @RequestParam("itemId") Long itemId,
            OrderItemUpdateDto orderItemUpdateDto
    ) {
        return orderItemService.updateItemOnOrder(orderId, itemId, orderItemUpdateDto);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    @RolesAllowed("user")
    public void removeItemFromOrder(
            @RequestParam("orderId") Long orderId,
            @RequestParam("itemId") Long itemId
    ) {
        orderItemService.removeItemFromOrder(orderId, itemId);
    }
}
