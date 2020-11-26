package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.novoautor.AutorEntity;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
        livroResponse.setCategoriaId(livroEntity.getCategoria().getCategoriaId());
        livroResponse.setAutores(getAutoresIds(livroEntity.getAutores()));

        livroResponse.add(livroLinks.linkToLivros("livros"));
        
        return livroResponse;
    }

    private List<Long> getAutoresIds(List<AutorEntity> autores) {
        List<Long> autoresIds = new ArrayList<>();
        autores.forEach(autor -> autoresIds.add(autor.getAutorId()));
        return autoresIds;
    }

    @Override
    public CollectionModel<LivroResponse> toCollectionModel(Iterable<? extends LivroEntity> entities) {
        CollectionModel<LivroResponse> collectionModel = super.toCollectionModel(entities);
        collectionModel.add(livroLinks.linkToLivros());

        return collectionModel;
    }
}
