package br.com.zup.casadocodigo.cadastrocategoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CategoriaResponseAssembler extends RepresentationModelAssemblerSupport<CategoriaEntity, CategoriaResponse> {

    private final CategoriaLinks categoriaLinks;

    public CategoriaResponseAssembler(CategoriaLinks categoriaLinks) {
        super(CategoriasController.class, CategoriaResponse.class);
        this.categoriaLinks = categoriaLinks;
    }

    @Override
    public CategoriaResponse toModel(CategoriaEntity categoriaEntity) {
        CategoriaResponse categoriasResponse = createModelWithId(categoriaEntity.getCategoriaId(), categoriaEntity);
        categoriasResponse.setCategoriaId(categoriaEntity.getCategoriaId());
        categoriasResponse.setCategoriaId(categoriaEntity.getCategoriaId());

        categoriasResponse.add(categoriaLinks.linkToCategorias("categorias"));

        return categoriasResponse;
    }

    @Override
    public CollectionModel<CategoriaResponse> toCollectionModel(Iterable<? extends CategoriaEntity> entities) {
        CollectionModel<CategoriaResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(categoriaLinks.linkToCategorias());

        return collectionModel;
    }
}

