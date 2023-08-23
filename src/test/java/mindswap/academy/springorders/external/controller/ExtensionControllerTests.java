package mindswap.academy.springorders.external.controller;

import mindswap.academy.springorders.SpringOrdersApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.given;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={SpringOrdersApplication.class}, webEnvironment= SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ExtensionControllerTests {

    @Test
    public void alo() {
        given().get("/restcallapi/id/1").then().statusCode(200);
    }
}
