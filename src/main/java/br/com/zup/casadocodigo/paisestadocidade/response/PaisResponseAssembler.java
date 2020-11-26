package br.com.zup.casadocodigo.paisestadocidade.response;

import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;
import br.com.zup.casadocodigo.paisestadocidade.PaisesController;
import br.com.zup.casadocodigo.paisestadocidade.links.PaisLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class PaisResponseAssembler extends RepresentationModelAssemblerSupport<PaisEntity, PaisResponse> {

    private final PaisLinks paisLinks;

    public PaisResponseAssembler(PaisLinks paisLinks) {
        super(PaisesController.class, PaisResponse.class);
        this.paisLinks = paisLinks;
    }

    @Override
    public PaisResponse toModel(PaisEntity paisEntity) {
        PaisResponse cidadeResponse = createModelWithId(paisEntity.getPaisId(), paisEntity);
        cidadeResponse.setPaisId(paisEntity.getPaisId());
        cidadeResponse.setNome(paisEntity.getNome());

        cidadeResponse.add(paisLinks.linkToPaises("paises"));

        return cidadeResponse;
    }

    @Override
    public CollectionModel<PaisResponse> toCollectionModel(Iterable<? extends PaisEntity> entities) {
        CollectionModel<PaisResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(paisLinks.linkToPaises());

        return collectionModel;
    }
}
