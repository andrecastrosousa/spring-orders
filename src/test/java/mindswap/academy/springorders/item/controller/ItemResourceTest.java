package mindswap.academy.springorders.item.controller;

import mindswap.academy.springorders.item.dto.ItemCreateDto;
import mindswap.academy.springorders.item.dto.ItemDto;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ItemResourceTest {
    ItemDto itemDto = new ItemDto(1L, 50.0F, "toalha");

    ItemCreateDto itemCreateDto = new ItemCreateDto(50.0F, "toalha");

    @Nested
    @Tag("validations")
    @DisplayName("item invalid crud")
    class ItemCrudValidator {


        @Test
        @DisplayName("Get an item which not exists")
        public void getItemNotFound() {
            given()
                    .when()
                    .get("/api/items/" + 100)
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Update an item which not exists")
        public void updateItemNotFound() {
            given()
                    .header("Content-Type", "application/json")
                    .body(itemDto)
                    .when()
                    .put("/api/items/" + 10)
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }

        @Test
        @DisplayName("Delete an item which not exists")
        public void deleteItemNotFound() {
            given()
                    .when()
                    .delete("/api/items/15")
                    .then()
                    .statusCode(HttpStatus.SC_NOT_FOUND);
        }
    }

    @Nested
    @Tag("crud")
    @DisplayName("User valid crud")
    class ItemCrudTests {
        @Test
        @DisplayName("Create an item")
        public void createItem() {
            given()
                    .header("Content-Type", "application/json")
                    .body(itemCreateDto)
                    .when()
                    .post("/api/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(2))
                    .body("price", is(50.0F))
                    .body("name", is(itemCreateDto.getName()));
        }

        @Test
        @DisplayName("Get list of items as admin")
        public void listItemsAsAdmin() {
            given()
                    .when()
                    .get("/api/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Get an item as admin")
        public void listItemAsAdmin() {
            given()
                    .when()
                    .get("/api/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("price", is(50.0F))
                    .body("name", is(itemCreateDto.getName()));
        }

        @Test
        @DisplayName("Get list of items as user")
        public void listItemsAsUser() {
            given()
                    .when()
                    .get("/api/items")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("size()", is(1));
        }

        @Test
        @DisplayName("Get an item as user")
        public void listItemAsUser() {
            given()
                    .when()
                    .get("/api/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("price", is(50.0F))
                    .body("name", is(itemCreateDto.getName()));
        }

        @Test
        @DisplayName("Update an item")
        public void updateItem() {
            ItemCreateDto itemUpdated = new ItemCreateDto(2, "copo");

            given()
                    .header("Content-Type", "application/json")
                    .body(itemUpdated)
                    .when()
                    .put("/api/items/" + 1)
                    .then()
                    .statusCode(HttpStatus.SC_OK)
                    .body("id", is(1))
                    .body("price", is(2.0F))
                    .body("name", is(itemUpdated.getName()));
        }


        @Test
        @DisplayName("Delete an item")
        public void deleteItem() {
            given()
                    .when()
                    .delete("/api/items/1")
                    .then()
                    .statusCode(HttpStatus.SC_NO_CONTENT);

        }
    }
}
