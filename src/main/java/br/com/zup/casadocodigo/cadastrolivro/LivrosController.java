package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/livros", produces = MediaType.APPLICATION_JSON_VALUE)
public class LivrosController {

    private final EntityManager manager;
    private final LivroRepository livroRepository;
    private final LivroResponseAssembler livroResponseAssembler;

    private static final String LIVRO_NAO_ENCONTRADO_ID_MSG = "Não existe um cadastro de livro com código %s";

    public LivrosController(EntityManager manager, LivroRepository livroRepository, LivroResponseAssembler livroResponseAssembler) {
        this.manager = manager;
        this.livroRepository = livroRepository;
        this.livroResponseAssembler = livroResponseAssembler;
    }

    @GetMapping
    public PagedModel<LivroResponse> listarTodos(@PageableDefault Pageable pageable,
                                                 PagedResourcesAssembler<LivroEntity> pagedResourcesAssembler) {

        Page<LivroEntity> livrosPage = livroRepository.findAll(pageable);

        return pagedResourcesAssembler
                .toModel(livrosPage, livroResponseAssembler);
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public LivroResponse cria(@RequestBody @Valid LivroRequest livroRequest) {

        LivroEntity novoLivro = livroRequest.toModel(manager);
        novoLivro = livroRepository.save(novoLivro);

        LivroResponse livroResponse = livroResponseAssembler.toModel(novoLivro);
        ResourceUriHelper.addUriInResponseHeader(novoLivro.getLivroIsbn());

        return livroResponse;
    }

    @GetMapping("/{livroIsbn}")
    public LivroResponse buscaPorIsbn(@PathVariable String livroIsbn) {
        LivroEntity livroEntity = livroRepository.findById(livroIsbn).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(LIVRO_NAO_ENCONTRADO_ID_MSG, livroIsbn)));

        return livroResponseAssembler.toModel(livroEntity);
    }
}
