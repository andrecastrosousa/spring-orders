package mindswap.academy.springorders.external.service;

import mindswap.academy.springorders.external.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class ExternalService {
    private final WebClient webClient;

    @Autowired
    ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Pet getQuarkusExtension(String id) {
        AtomicReference<Pet> pet = null;
        Flux<Pet> monoPet = webClient.get().uri("/pet/" + id).retrieve().bodyToFlux(Pet.class);
        monoPet.subscribe(consumer -> pet.set(consumer));
        return pet.get();
    }

}
