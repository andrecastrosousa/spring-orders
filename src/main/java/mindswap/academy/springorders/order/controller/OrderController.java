package mindswap.academy.springorders.order.controller;

import jakarta.annotation.security.RolesAllowed;
import jakarta.transaction.Transactional;
import jakarta.websocket.server.PathParam;
import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController(value = "/orders")
public class OrderController {

    @Autowired
    OrderService orderService;

    @GetMapping
    public List<OrderDto> list() {
        return orderService.listAll();
    }

    @GetMapping("/{orderId}")
    public OrderDto get(@PathParam("orderId") Long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping
    @Transactional
    public OrderDto create(OrderCreateDto order) {
        return orderService.create(order);
    }

    @DeleteMapping("/{orderId}")
    @Transactional
    public void delete(@PathParam("orderId") Long orderId) {
        orderService.delete(orderId);
    }
}
