package br.com.zup.casadocodigo.cadastrocategoria;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

@Component
public class CategoriaLinks {

    public Link linkToCategoria(Long categoriaId, String rel) {
        return linkTo(methodOn(CategoriasController.class)
                .buscaPorId(categoriaId)).withRel(rel);
    }

    public Link linkToCategoria(Long categoriaId) {
        return linkToCategoria(categoriaId, IanaLinkRelations.SELF.value());
    }

    public Link linkToCategorias(String rel) {
        return linkTo(CategoriasController.class).withRel(rel);
    }

    public Link linkToCategorias() {
        return linkToCategorias(IanaLinkRelations.SELF.value());
    }
}
