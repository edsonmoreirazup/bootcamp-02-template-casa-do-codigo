package br.com.zup.casadocodigo.paisestadocidade.response;

import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import br.com.zup.casadocodigo.paisestadocidade.CidadesController;
import br.com.zup.casadocodigo.paisestadocidade.links.CidadeLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class CidadeResponseAssembler extends RepresentationModelAssemblerSupport<CidadeEntity, CidadeResponse> {

    private final CidadeLinks cidadeLinks;

    public CidadeResponseAssembler(CidadeLinks cidadeLinks) {
        super(CidadesController.class, CidadeResponse.class);
        this.cidadeLinks = cidadeLinks;
    }

    @Override
    public CidadeResponse toModel(CidadeEntity cidadeEntity) {
        CidadeResponse cidadeResponse = createModelWithId(cidadeEntity.getCidadeId(), cidadeEntity);
        cidadeResponse.setCidadeId(cidadeEntity.getCidadeId());
        cidadeResponse.setEstadoId(cidadeEntity.getEstado().getEstadoId());
        cidadeResponse.setNome(cidadeEntity.getNome());

        cidadeResponse.add(cidadeLinks.linkToCidades("cidades"));

        return cidadeResponse;
    }

    @Override
    public CollectionModel<CidadeResponse> toCollectionModel(Iterable<? extends CidadeEntity> entities) {
        CollectionModel<CidadeResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(cidadeLinks.linkToCidades());

        return collectionModel;
    }
}
