package br.com.zup.casadocodigo.novoautor;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class AutorResponseAssembler extends RepresentationModelAssemblerSupport<AutorEntity, AutorResponse> {

    private final AutorLinks autorLinks;

    public AutorResponseAssembler(AutorLinks autorLinks) {
        super(AutoresController.class, AutorResponse.class);
        this.autorLinks = autorLinks;
    }

    @Override
    public AutorResponse toModel(AutorEntity autorEntity) {
        AutorResponse autorResponse = createModelWithId(autorEntity.getAutorId(), autorEntity);
        autorResponse.setAutorId(autorEntity.getAutorId());
        autorResponse.setDataRegistro(autorEntity.getDataRegistro());
        autorResponse.setDescricao(autorEntity.getDescricao());
        autorResponse.setEmail(autorEntity.getEmail());
        autorResponse.setNome(autorEntity.getNome());

        autorResponse.add(autorLinks.linkToAutores("autores"));

        return autorResponse;
    }

    @Override
    public CollectionModel<AutorResponse> toCollectionModel(Iterable<? extends AutorEntity> entities) {
        CollectionModel<AutorResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(autorLinks.linkToAutores());

        return collectionModel;
    }
}

