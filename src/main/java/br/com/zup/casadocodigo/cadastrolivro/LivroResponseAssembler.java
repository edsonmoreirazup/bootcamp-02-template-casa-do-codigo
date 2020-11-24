package br.com.zup.casadocodigo.cadastrolivro;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

@Component
public class LivroResponseAssembler extends RepresentationModelAssemblerSupport<LivroEntity, LivroResponse> {

    private final LivroLinks livroLinks;

    public LivroResponseAssembler(LivroLinks livroLinks){
        super(LivrosController.class, LivroResponse.class);
        this.livroLinks = livroLinks;
    }

    @Override
    public LivroResponse toModel(LivroEntity livroEntity) {
       LivroResponse livroResponse = createModelWithId(livroEntity.getLivroIsbn(), livroEntity);
       livroResponse.setLivroIsbn(livroEntity.getLivroIsbn());
       livroResponse.setTitulo(livroEntity.getTitulo());
       livroResponse.setResumo(livroEntity.getResumo());
       livroResponse.setSumario(livroEntity.getSumario());
       livroResponse.setPreco(livroEntity.getPreco());
       livroResponse.setNrPaginas(livroEntity.getNrPaginas());
       livroResponse.setDataPublicacao(livroEntity.getDataPublicacao());

        livroResponse.add(livroLinks.linkToLivros("livros"));
        
        return livroResponse;
    }

    @Override
    public CollectionModel<LivroResponse> toCollectionModel(Iterable<? extends LivroEntity> entities) {
        CollectionModel<LivroResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(livroLinks.linkToLivros());

        return collectionModel;
    }
}
