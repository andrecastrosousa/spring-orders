package mindswap.academy.springorders.order.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;
import mindswap.academy.springorders.order.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderItemController {
    OrderItemService orderItemService;

    @Autowired
    OrderItemController(OrderItemService orderItemService) {
        this.orderItemService = orderItemService;
    }

    @GetMapping("/{orderId}/items")
    @RolesAllowed("user")
    public List<OrderItemDto> get(@PathVariable("orderId") Long orderId) {
        return orderItemService.getListOfOrderItem(orderId);
    }

    @PutMapping("/{orderId}/items")
    @Transactional
    public OrderDto addItemToOrder(
            @PathVariable("orderId") Long orderId,
            @RequestBody OrderItemDto orderItemAddDto
    ) {
        return orderItemService.addItemToOrder(orderId, orderItemAddDto);
    }

    @PutMapping("/{orderId}/items/{itemId}")
    @Transactional
    public OrderDto updateItemOnOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("itemId") Long itemId,
            @RequestBody OrderItemUpdateDto orderItemUpdateDto
    ) {
        return orderItemService.updateItemOnOrder(orderId, itemId, orderItemUpdateDto);
    }

    @DeleteMapping("/{orderId}/items/{itemId}")
    @RolesAllowed("user")
    public void removeItemFromOrder(
            @PathVariable("orderId") Long orderId,
            @PathVariable("itemId") Long itemId
    ) {
        orderItemService.removeItemFromOrder(orderId, itemId);
    }
}
