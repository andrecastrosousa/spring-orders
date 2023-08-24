package mindswap.academy.springorders.external.service;

import mindswap.academy.springorders.external.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class ExternalService {
    private final WebClient webClient;

    @Autowired
    ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Pet getQuarkusExtension(String id) {
        Pet pet = webClient.get().uri("/pet/" + id).retrieve().bodyToMono(Pet.class).block();
        return pet;
    }

}
