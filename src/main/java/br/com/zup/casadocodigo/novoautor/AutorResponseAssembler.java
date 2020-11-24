package br.com.zup.casadocodigo.novoautor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AutorResponseAssembler extends RepresentationModelAssemblerSupport<AutorEntity, AutorResponse> {

    private final AutorLinks categoriaLinks;

    public AutorResponseAssembler(AutorLinks categoriaLinks) {
        super(AutoresController.class, AutorResponse.class);
        this.categoriaLinks = categoriaLinks;
    }

    @Override
    public AutorResponse toModel(AutorEntity categoriaEntity) {
        AutorResponse categoriasResponse = createModelWithId(categoriaEntity.getAutorId(), categoriaEntity);
        categoriasResponse.setAutorId(categoriaEntity.getAutorId());
        categoriasResponse.setAutorId(categoriaEntity.getAutorId());

        categoriasResponse.add(categoriaLinks.linkToAutores("categorias"));

        return categoriasResponse;
    }

    @Override
    public CollectionModel<AutorResponse> toCollectionModel(Iterable<? extends AutorEntity> entities) {
        CollectionModel<AutorResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(categoriaLinks.linkToAutores());

        return collectionModel;
    }
}

