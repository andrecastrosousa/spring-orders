package mindswap.academy.springorders.external.controller;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import static io.restassured.RestAssured.given;

@ExtendWith(MockitoExtension.class)
public class ExtensionControllerTests {

    @Mock
    WebClient webClientMock;

    @Test
    public void alo() {
        /*Integer employeeId = 100;
        Extension mockEmployee = new Extension("100", "Adam", "Sandler", new ArrayList<>(List.of("")));
        when(webClientMock.get())
                .thenReturn(requestHeadersUriSpecMock);
        when(requestHeadersUriMock.uri("/employee/{id}", employeeId))
                .thenReturn(requestHeadersSpecMock);
        when(requestHeadersMock.retrieve())
                .thenReturn(responseSpecMock);
        when(responseMock.bodyToMono(Employee.class))
                .thenReturn(Mono.just(mockEmployee));

        Mono<Extension> employeeMono = employeeService.getEmployeeById(employeeId);

        StepVerifier.create(employeeMono)
                .expectNextMatches(employee -> employee.getRole()
                        .equals(Role.LEAD_ENGINEER))
                .verifyComplete();



        given()
                .get("/restcallapi/id/1")
                .then()
                .statusCode(200);*/
    }
}
