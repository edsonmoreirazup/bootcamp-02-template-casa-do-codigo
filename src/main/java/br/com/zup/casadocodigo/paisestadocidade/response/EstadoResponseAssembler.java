package br.com.zup.casadocodigo.paisestadocidade.response;

import br.com.zup.casadocodigo.paisestadocidade.EstadoEntity;
import br.com.zup.casadocodigo.paisestadocidade.EstadosController;
import br.com.zup.casadocodigo.paisestadocidade.links.EstadoLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class EstadoResponseAssembler extends RepresentationModelAssemblerSupport<EstadoEntity, EstadoResponse> {

    private final EstadoLinks estadoLinks;

    public EstadoResponseAssembler(EstadoLinks estadoLinks) {
        super(EstadosController.class, EstadoResponse.class);
        this.estadoLinks = estadoLinks;
    }

    @Override
    public EstadoResponse toModel(EstadoEntity estadoEntity) {
        EstadoResponse estadoResponse = createModelWithId(estadoEntity.getEstadoId(), estadoEntity);
        estadoResponse.setEstadoId(estadoEntity.getEstadoId());
        estadoResponse.setPaisId(estadoEntity.getPais().getPaisId());
        estadoResponse.setNome(estadoEntity.getNome());

        estadoResponse.add(estadoLinks.linkToEstados("estados"));

        return estadoResponse;
    }

    @Override
    public CollectionModel<EstadoResponse> toCollectionModel(Iterable<? extends EstadoEntity> entities) {
        CollectionModel<EstadoResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(estadoLinks.linkToEstados());

        return collectionModel;
    }
}
