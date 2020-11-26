package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriasController {

    private final CategoriaRepository categoriaRepository;
    private final CategoriaResponseAssembler categoriaResponseAssembler;
    private static final String CATEGORIA_NAO_ENCONTRADA_MSG = "Não existe um cadastro de categoria com código %d";
    private static final String CATEGORIA_NAO_NOME_MSG = "Não existe um cadastro de categoria com nome %s";

    public CategoriasController(CategoriaRepository categoriaRepository, CategoriaResponseAssembler categoriaResponseAssembler) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaResponseAssembler = categoriaResponseAssembler;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CategoriaResponse cria(@RequestBody @Valid CategoriaRequest categoriaRequest) {

        CategoriaEntity novaCategoria = new CategoriaEntity(categoriaRequest.getNome());
        novaCategoria = categoriaRepository.save(novaCategoria);

        CategoriaResponse categoriaResponse = categoriaResponseAssembler.toModel(novaCategoria);
        ResourceUriHelper.addUriInResponseHeader(novaCategoria.getCategoriaId());

        return categoriaResponse;
    }

    @GetMapping("/{categoriaId}")
    public CategoriaResponse buscaPorId(@PathVariable Long categoriaId) {
        CategoriaEntity categoria = categoriaRepository.findById(categoriaId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(CATEGORIA_NAO_ENCONTRADA_MSG, categoriaId)));

        return categoriaResponseAssembler.toModel(categoria);
    }

    @GetMapping
    public CategoriaResponse buscaPorNome(@RequestBody @Valid CategoriaRequest categoriaRequest) {
        CategoriaEntity categoria = categoriaRepository.findByNome(categoriaRequest.getNome()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(CATEGORIA_NAO_NOME_MSG, categoriaRequest.getNome())));

        return categoriaResponseAssembler.toModel(categoria);
    }
}
