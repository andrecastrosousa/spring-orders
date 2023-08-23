package mindswap.academy.springorders.order.service;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.item.repository.ItemRepository;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.converter.OrderItemConverter;
import mindswap.academy.springorders.order.dto.OrderDto;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.model.OrderItem;
import mindswap.academy.springorders.order.repository.OrderItemRepository;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    ItemRepository itemRepository;
    OrderConverter orderConverter;
    OrderItemConverter orderItemConverter;

    @Autowired
    OrderItemServiceImpl(
            OrderRepository orderRepository,
            OrderConverter orderConverter,
            OrderItemConverter orderItemConverter,
            OrderItemRepository orderItemRepository,
            ItemRepository itemRepository
    ) {
        this.orderConverter = orderConverter;
        this.orderRepository = orderRepository;
        this.orderItemRepository = orderItemRepository;
        this.orderItemConverter = orderItemConverter;
        this.itemRepository = itemRepository;
    }

    @Override
    public List<OrderItemDto> getListOfOrderItem(Long orderId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }

        return order.getOrderItems().stream().map(orderItem -> orderItemConverter.toDto(orderItem)).toList();
    }

    @Override
    public OrderDto addItemToOrder(Long orderId, OrderItemDto orderItemAddDto) {
        Order order = orderRepository.findById(orderId).orElse(null);
        Item item = itemRepository.findById(orderItemAddDto.getItem().getId()).orElse(null);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        OrderItem orderItem = orderItemRepository.findByOrderIdAndItemId(orderId, item.getId());
        int currentQuantity = 0;
        if (orderItem != null) {
            currentQuantity = orderItem.getQuantity();
        } else {
            orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(item);
        }
        order.setTotal(recalculateTotal(order, item, currentQuantity, orderItemAddDto.getQuantity()));
        orderItem.setQuantity(orderItemAddDto.getQuantity());

        orderRepository.save(order);
        orderItemRepository.save(orderItem);


        List<OrderItem> orderItems = order.getOrderItems();
        orderItems.add(orderItem);
        order.setOrderItems(orderItems);

        return orderConverter.toDto(order);
    }

    @Override
    public OrderDto updateItemOnOrder(Long orderId, Long itemId, OrderItemUpdateDto orderItemUpdateDto) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        OrderItem orderItem = orderItemRepository.findByOrderIdAndItemId(orderId, itemId);
        if (orderItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }

        order.setTotal(
                recalculateTotal(
                        order,
                        item,
                        orderItem.getQuantity(),
                        orderItemUpdateDto.getQuantity()
                ));
        orderItem.setQuantity(orderItemUpdateDto.getQuantity());
        orderItemRepository.save(orderItem);
        orderRepository.save(order);

        return orderConverter.toDto(order);
    }

    @Override
    @Transactional
    public void removeItemFromOrder(Long orderId, Long itemId) {
        Order order = orderRepository.findById(orderId).orElse(null);
        if (order == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found");
        }
        Item item = itemRepository.findById(itemId).orElse(null);
        if (item == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item not found");
        }
        OrderItem orderItem = orderItemRepository.findByOrderIdAndItemId(orderId, itemId);
        if (orderItem == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Item on order not found");
        }

        orderItemRepository.delete(orderItem);
    }

    protected double recalculateTotal(Order order, Item item, int previousQuantity, int newQuantity) {
        double itemPrice = item.getPrice();
        double totalToDecrement = itemPrice * previousQuantity;
        double totalToIncrement = itemPrice * newQuantity;

        return order.getTotal() - totalToDecrement + totalToIncrement;
    }
}
