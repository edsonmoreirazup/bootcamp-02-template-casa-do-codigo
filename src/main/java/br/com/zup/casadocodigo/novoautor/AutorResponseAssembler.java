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
    public AutorResponse toModel(AutorEntity autorEntity) {
        AutorResponse autorResponse = createModelWithId(autorEntity.getAutorId(), autorEntity);
        autorResponse.setDataRegistro(autorEntity.getDataRegistro());
        autorResponse.setDescricao(autorEntity.getDescricao());
        autorResponse.setEmail(autorEntity.getEmail());
        autorResponse.setNome(autorEntity.getNome());

        autorResponse.add(categoriaLinks.linkToAutores("autores"));

        return autorResponse;
    }

    @Override
    public CollectionModel<AutorResponse> toCollectionModel(Iterable<? extends AutorEntity> entities) {
        CollectionModel<AutorResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(categoriaLinks.linkToAutores());

        return collectionModel;
    }
}

