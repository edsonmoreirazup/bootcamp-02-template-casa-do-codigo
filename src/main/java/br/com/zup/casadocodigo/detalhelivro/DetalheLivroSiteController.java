package br.com.zup.casadocodigo.detalhelivro;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.cadastrolivro.LivroRepository;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;

@RestController
@RequestMapping(path = "/produtos", produces = MediaType.APPLICATION_JSON_VALUE)
public class DetalheLivroSiteController {

    private final LivroRepository livroRepository;
    private final DetalheSiteLivroResponseAssembler detalheSiteLivroResponseAssembler;
    private static final String LIVRO_NAO_ENCONTRADO_MSG = "Não existe um cadastro de livro com código %s";

    public DetalheLivroSiteController(LivroRepository livroRepository, DetalheSiteLivroResponseAssembler detalheSiteLivroResponseAssembler) {
        this.livroRepository = livroRepository;
        this.detalheSiteLivroResponseAssembler = detalheSiteLivroResponseAssembler;
    }

    @GetMapping(value = "/{id}")
    public DetalheSiteLivroResponse detalhe(@PathVariable("id") String livroId) {

        LivroEntity livroBuscado = livroRepository.findById(livroId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(LIVRO_NAO_ENCONTRADO_MSG, livroId)));

        return detalheSiteLivroResponseAssembler.toModel(livroBuscado);
    }
}
