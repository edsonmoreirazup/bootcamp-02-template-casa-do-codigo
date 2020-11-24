package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivrosController {

    private final LivroRepository livroRepository;
    private final LivroResponseAssembler livroResponseAssembler;
    private static final String LIVRO_NAO_ENCONTRADO_ID_MSG = "Não existe um cadastro de livro com código %s";

    public LivrosController(LivroRepository livroRepository, LivroResponseAssembler livroResponseAssembler) {
        this.livroRepository = livroRepository;
        this.livroResponseAssembler = livroResponseAssembler;
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public LivroResponse cria(@RequestBody @Valid LivroRequest request) {

        LivroEntity novoLivro = new LivroEntity(request.getLivroIsbn(),request.getTitulo(),request.getResumo(), request.getSumario(),
                request.getPreco(), request.getNrPaginas(), request.getDataPublicacao(), request.getAutores(), request.getCategoria());
        novoLivro = livroRepository.save(novoLivro);

        LivroResponse livroResponse = livroResponseAssembler.toModel(novoLivro);
        ResourceUriHelper.addUriInResponseHeader(novoLivro.getLivroIsbn());

        return livroResponse;
    }

    @GetMapping("/{livroIsbn}")
    public LivroResponse buscaPorId(@PathVariable String livroIsbn) {
        LivroEntity livroEntity = livroRepository.findById(livroIsbn).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(LIVRO_NAO_ENCONTRADO_ID_MSG, livroIsbn)));

        return livroResponseAssembler.toModel(livroEntity);
    }
}
