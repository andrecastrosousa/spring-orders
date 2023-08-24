package mindswap.academy.springorders.external.controller;

import jakarta.annotation.security.PermitAll;
import mindswap.academy.springorders.external.model.Pet;
import mindswap.academy.springorders.external.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/callrestapi")
public class ExtensionsController {
    ExternalService externalService;

    @Autowired
    ExtensionsController(ExternalService externalService) {
        this.externalService = externalService;
    }

    @GetMapping("/id/{id}")
    @PermitAll
    public Pet get(@PathVariable("id") String id) {
        return externalService.getQuarkusExtension(id);
    }
}
