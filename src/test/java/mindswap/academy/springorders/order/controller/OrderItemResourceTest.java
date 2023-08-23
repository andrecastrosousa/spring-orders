package mindswap.academy.springorders.order.controller;

import io.restassured.http.ContentType;
import jakarta.transaction.Transactional;
import mindswap.academy.springorders.SpringOrdersApplication;
import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.item.repository.ItemRepository;
import mindswap.academy.springorders.order.converter.OrderConverter;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;
import mindswap.academy.springorders.order.model.Order;
import mindswap.academy.springorders.order.model.OrderItem;
import mindswap.academy.springorders.order.repository.OrderItemRepository;
import mindswap.academy.springorders.order.repository.OrderRepository;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringOrdersApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class OrderItemResourceTest {

    @Autowired
    OrderItemRepository orderItemRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ItemRepository itemRepository;

    OrderItemDto orderItemAddDto = new OrderItemDto();

    OrderItemUpdateDto orderItemUpdateDto = new OrderItemUpdateDto();

    @BeforeAll
    public void setup() {
        Order order = new Order();
        orderRepository.save(order);

        Item item = new Item();
        item.setPrice(2);
        item.setName("copo");
        itemRepository.save(item);

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        orderItem.setQuantity(3);
        orderItemRepository.save(orderItem);
    }

    @Nested
    @Tag("errors")
    @DisplayName("Errors on Items of order CRUD")
    class OrderItemErrorCrud {
        @Test
        @DisplayName("List items of a non existent order")
        public void listItemsOfNonExistentOrder() {
            given()
                    .when()
                    .get("/api/orders/20/items")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Add a non existent item to an order")
        public void addNonExistentItemToOrder() {
            Item item = new Item();
            item.setName("copo");
            item.setPrice(2);
            item.setId(20L);

            orderItemAddDto.setItem(item);
            orderItemAddDto.setQuantity(5);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemAddDto)
                    .when()
                    .put("/api/orders/1/items")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Add item to a non existent order")
        public void addItemToNonExistentOrder() {
            Item item = new Item();
            item.setName("copo");
            item.setPrice(2);
            item.setId(1L);

            orderItemAddDto.setItem(item);
            orderItemAddDto.setQuantity(5);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemAddDto)
                    .when()
                    .put("/api/orders/20/items")
                    .then()
                    .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }

        @Test
        @DisplayName("Update a non existent item on order")
        public void updateNonExistentItemOnOrder() {
            orderItemUpdateDto.setId(1L);
            orderItemUpdateDto.setQuantity(3);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemUpdateDto)
                    .when()
                    .put("/api/orders/1/items/20")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Update item on non existent order")
        public void updateItemOnNonExistentOrder() {
            orderItemUpdateDto.setId(1L);
            orderItemUpdateDto.setQuantity(3);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemUpdateDto)
                    .when()
                    .put("/api/orders/20/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Remove a non existent item from order")
        public void removeItemFromOrder() {
            given()
                    .delete("/api/orders/1/items/20")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Remove item from non existent order")
        public void removeItemFromNonExistentOrder() {
            given()
                    .delete("/api/orders/20/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }
    }

    @Nested
    @Tag("crud")
    @DisplayName("Items of order CRUD")
    class OrderItemCrudTests {
        @Test
        @DisplayName("List items of an order")
        public void listItemsOfOrder() {
            given()
                    .when()
                    .get("/api/orders/1/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Add Item to Order")
        public void addItemToOrder() {
            Item item = new Item();
            item.setName("copo");
            item.setPrice(2);
            item.setId(1L);

            orderItemAddDto.setItem(item);
            orderItemAddDto.setQuantity(5);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemAddDto)
                    .when()
                    .put("/api/orders/1/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("total", is(10.0F))
                    .body("orderItems.size()", is(1));

        }

        @Test
        @DisplayName("Update Item on Order")
        public void updateItemOnOrder() {
            orderItemUpdateDto.setId(1L);
            orderItemUpdateDto.setQuantity(3);

            given()
                    .contentType(ContentType.JSON)
                    .body(orderItemUpdateDto)
                    .when()
                    .put("/api/orders/1/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("total", is(6.0F))
                    .body("orderItems.size()", is(1));
        }

        @Test
        @DisplayName("Remove Item from Order")
        public void removeItemFromOrder() {
            given()
                    .when()
                    .get("/api/orders/1/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(0));


            given()
                    .delete("/api/orders/1/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK);
        }
    }
}
