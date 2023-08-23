package mindswap.academy.springorders.order.controller;

import mindswap.academy.springorders.order.dto.OrderCreateDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;


import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;


public class OrderResourceTest {

    OrderCreateDto orderCreateDto = new OrderCreateDto();

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
                    .statusCode(200)
                    .body("id", is(2))
                    .body("total", is(0.0F));
        }

        @Test
        @DisplayName("Get a list of orders associated to user")
        public void getOrders() {
            given()
                    .get("/api/orders")
                    .then()
                    .statusCode(200)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Delete an orders associated to a user")
        public void deleteOrder() {
            given()
                    .delete("/api/orders/1")
                    .then()
                    .statusCode(204);

        }
    }
}
