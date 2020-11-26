package br.com.zup.casadocodigo.paisestadocidade.links;

import br.com.zup.casadocodigo.paisestadocidade.PaisesController;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class PaisLinks {

    public Link linkToPais(Long paisId, String rel) {
        return linkTo(methodOn(PaisesController.class)
                .buscaPorId(paisId)).withRel(rel);
    }

    public Link linkToPais(Long paisId) {
        return linkToPais(paisId, IanaLinkRelations.SELF.value());
    }

    public Link linkToPaises(String rel) {
        return linkTo(PaisesController.class).withRel(rel);
    }

    public Link linkToPaises() {
        return linkToPaises(IanaLinkRelations.SELF.value());
    }
}
