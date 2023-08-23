package mindswap.academy.springorders.external.controller;

import mindswap.academy.springorders.external.model.Extension;
import mindswap.academy.springorders.external.service.ExternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping("/callrestapi")
public class ExtensionsController {

    @Autowired
    ExternalService externalService;

    @GetMapping("/id/{id}")
    public Set<Extension> get(@PathVariable("id") String id) {
        return externalService.getQuarkusExtension(id);
    }


    /*@GetMapping("/pet/{id}")
    public Pet getById(@PathVariable("id") Long id) {
        return externalService.getPetById(id);
    }*/
}
