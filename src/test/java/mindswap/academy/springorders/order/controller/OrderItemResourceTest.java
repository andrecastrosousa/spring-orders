package mindswap.academy.springorders.order.controller;


import io.restassured.http.ContentType;
import mindswap.academy.springorders.item.model.Item;
import mindswap.academy.springorders.order.dto.OrderItemDto;
import mindswap.academy.springorders.order.dto.OrderItemUpdateDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

@SpringBootTest
public class OrderItemResourceTest {
    OrderItemDto orderItemAddDto = new OrderItemDto();

    OrderItemUpdateDto orderItemUpdateDto = new OrderItemUpdateDto();

    @Nested
    @Tag("errors")
    @DisplayName("Errors on Items of order CRUD")
    class OrderItemErrorCrud {
        @Test
        @DisplayName("List items of a non existent order")
        public void listItemsOfNonExistentOrder() {
            given()
                    .when()
                    .get("/orders/20/items")
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
                    .put("/orders/1/items")
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
                    .put("/orders/20/items")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
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
                    .put("/orders/1/items/20")
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
                    .put("/orders/20/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Remove a non existent item from order")
        public void removeItemFromOrder() {
            given()
                    .delete("/orders/1/items/20")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Remove item from non existent order")
        public void removeItemFromNonExistentOrder() {
            given()
                    .delete("/orders/20/items/1")
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
                    .auth().preemptive().basic("andre@gmail.com", "ola123")
                    .get("/orders/1/items")
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
                    .put("/orders/1/items")
                    .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("total", is(4.0F))
                    .body("orderItems.size()", is(2));

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
                    .put("/orders/1/items/1")
                    .then()
                    .statusCode(200)
                    .body("id", is(1))
                    .body("total", is(0.0F))
                    .body("orderItems.size()", is(1));
        }

        @Test
        @DisplayName("Remove Item from Order")
        public void removeItemFromOrder() {
            given()
                    .get("/orders/1/items")
                    .then()
                    .statusCode(200)
                    .body("size()", is(1));


            given()
                    .delete("/orders/1/items/1")
                    .then()
                    .statusCode(204);
        }
    }
}
