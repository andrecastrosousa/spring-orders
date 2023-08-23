package mindswap.academy.springorders.external.service;

import mindswap.academy.springorders.external.model.Extension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Set;

@Service
public class ExternalService {
    private final WebClient webClient;

    @Autowired
    ExternalService(WebClient webClient) {
        this.webClient = webClient;
    }
    /*@RestClient
    ExtensionsService extensionsService;

    @RestClient
    PetService petService;


    public Set<Extension> getQuarkusExtension(String id) {
        return extensionsService.getById(id);
    }

    public Pet getPetById(Long id) {
        return petService.getById(id);
    }*/

    public Set<Extension> getQuarkusExtension(String id) {
        Extension extension = webClient.get().uri("/" + id).retrieve().bodyToMono(Extension.class).block();
        return Set.of(extension);
    }

}
