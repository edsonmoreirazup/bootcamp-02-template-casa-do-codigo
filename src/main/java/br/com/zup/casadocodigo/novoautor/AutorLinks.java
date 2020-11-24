package br.com.zup.casadocodigo.novoautor;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AutorLinks {

    public Link linkToAutor(Long autorId, String rel) {
        return linkTo(methodOn(AutoresController.class)
                .buscaPorId(autorId)).withRel(rel);
    }

    public Link linkToAutor(Long autorId) {
        return linkToAutor(autorId, IanaLinkRelations.SELF.value());
    }

    public Link linkToAutores(String rel) {
        return linkTo(AutoresController.class).withRel(rel);
    }

    public Link linkToAutores() {
        return linkToAutores(IanaLinkRelations.SELF.value());
    }
}
