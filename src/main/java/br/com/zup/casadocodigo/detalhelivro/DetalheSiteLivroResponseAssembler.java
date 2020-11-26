package br.com.zup.casadocodigo.detalhelivro;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.cadastrolivro.LivroLinks;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class DetalheSiteLivroResponseAssembler extends RepresentationModelAssemblerSupport<LivroEntity, DetalheSiteLivroResponse> {

    //TODO
    private final LivroLinks livroLinks;

    public DetalheSiteLivroResponseAssembler(LivroLinks livroLinks){
        super(DetalheSiteLivroResponseAssembler.class, DetalheSiteLivroResponse.class);
        this.livroLinks = livroLinks;
    }

    @Override
    public DetalheSiteLivroResponse toModel(LivroEntity livroEntity) {
        DetalheSiteLivroResponse detalheSiteLivroResponse = createModelWithId(livroEntity.getLivroIsbn(), livroEntity);
        detalheSiteLivroResponse.setAutores(livroEntity.getAutores());
        detalheSiteLivroResponse.setDataPublicacao(livroEntity.getDataPublicacao());
        detalheSiteLivroResponse.setIsbn(livroEntity.getLivroIsbn());
        detalheSiteLivroResponse.setNumeroPaginas(livroEntity.getNrPaginas());
        detalheSiteLivroResponse.setPreco(livroEntity.getPreco());
        detalheSiteLivroResponse.setResumo(livroEntity.getResumo());
        detalheSiteLivroResponse.setSumario(livroEntity.getSumario());
        detalheSiteLivroResponse.setTitulo(livroEntity.getTitulo());
        //TODO
        detalheSiteLivroResponse.add(livroLinks.linkToLivros("livros"));

        return detalheSiteLivroResponse;
    }

    @Override
    public CollectionModel<DetalheSiteLivroResponse> toCollectionModel(Iterable<? extends LivroEntity> entities) {
        CollectionModel<DetalheSiteLivroResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(livroLinks.linkToLivros());

        return collectionModel;
    }
}
