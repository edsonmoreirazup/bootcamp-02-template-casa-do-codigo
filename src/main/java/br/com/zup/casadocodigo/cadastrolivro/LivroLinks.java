package br.com.zup.casadocodigo.cadastrolivro;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LivroLinks {

    public Link linkToLivro(String livroIsbn, String rel) {
        return linkTo(methodOn(LivrosController.class)
                .buscaPorIsbn(livroIsbn)).withRel(rel);
    }

    public Link linkToLivro(String livroIsbn) {
        return linkToLivro(livroIsbn, IanaLinkRelations.SELF.value());
    }

    public Link linkToLivros(String rel) {
        return linkTo(LivrosController.class).withRel(rel);
    }

    public Link linkToLivros() {
        return linkToLivros(IanaLinkRelations.SELF.value());
    }
}
