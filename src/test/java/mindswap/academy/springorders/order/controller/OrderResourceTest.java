package mindswap.academy.springorders.order.controller;

import jakarta.transaction.Transactional;
import mindswap.academy.springorders.SpringOrdersApplication;
import mindswap.academy.springorders.item.repository.ItemRepository;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.dto.OrderCreateDto;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.repository.OrderItemRepository;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringOrdersApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class OrderResourceTest {
    OrderItemRepository orderItemRepository;
    OrderRepository orderRepository;
    ItemRepository itemRepository;
    OrderConverter orderConverter;

    @Autowired
    public OrderResourceTest(
        OrderItemRepository orderItemRepository,
        OrderRepository orderRepository,
        ItemRepository itemRepository,
        OrderConverter orderConverter
    ) {
        this.orderItemRepository = orderItemRepository;
        this.orderRepository = orderRepository;
        this.itemRepository = itemRepository;
        this.orderConverter = orderConverter;
    }

    OrderCreateDto orderCreateDto = new OrderCreateDto();

    @BeforeEach
    @Transactional
    public void before() {
        orderItemRepository.deleteAll();
        itemRepository.deleteAll();
        orderRepository.deleteAll();

        orderItemRepository.reset();
        itemRepository.reset();
        orderRepository.reset();

        orderCreateDto.setOrderDatetime(LocalDateTime.now());
        Order order = orderConverter.toEntityFromCreateDto(orderCreateDto);

        orderRepository.save(order);
    }

    @Nested
    @Tag("validations")
    @DisplayName("Orders invalid crud")
    class OrderCrudValidator {
        @Test
        @DisplayName("Get an order not founded")
        public void getOrderNotFound() {
            given()
                    .get("/api/orders/2")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Delete an order not founded")
        public void deleteOrderNotFound() {
            given()
                    .delete("/api/orders/2")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }
    }

    @Nested
    @Tag("crud")
    @DisplayName("Orders valid crud")
    class ItemCrudTests {
        @Test
        @DisplayName("Create an order and associate to a user")
        public void post() {
            given()
                    .header("Content-Type", "application/json")
                    .body(orderCreateDto)
                    .when()
                    .post("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(2))
                    .body("total", is(0.0F));
        }

        @Test
        @DisplayName("Get a list of orders associated to user")
        public void getOrders() {
            given()
                    .get("/api/orders")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Delete an orders associated to a user")
        public void deleteOrder() {
            given()
                    .delete("/api/orders/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK);

        }
    }
}
