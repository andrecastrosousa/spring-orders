package mindswap.academy.springorders.order.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/orders")
public class OrderController {
    OrderService orderService;

    @Autowired
    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDto> list() {
        return orderService.listAll();
    }

    @GetMapping("/{orderId}")
    public OrderDto get(@PathVariable("orderId") Long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
    @Transactional
    public OrderDto create(@RequestBody OrderCreateDto order) {
        return orderService.create(order);
    }

    @DeleteMapping("/{orderId}")
    @Transactional
    public void delete(@PathVariable("orderId") Long orderId) {
        orderService.delete(orderId);
    }
}
