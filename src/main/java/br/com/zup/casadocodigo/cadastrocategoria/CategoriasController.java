package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.compartilhado.exceptionhandler.DomainException;
import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/categorias", produces = MediaType.APPLICATION_JSON_VALUE)
public class CategoriasController {

    private CategoriaRepository categoriaRepository;
    private CategoriaResponseAssembler categoriaResponseAssembler;
    private static final String CATEGORIA_NAO_ENCONTRADA_MSG = "Não existe um cadastro de categoria com código %d";

    public CategoriasController(CategoriaRepository categoriaRepository, CategoriaResponseAssembler categoriaResponseAssembler) {
        this.categoriaRepository = categoriaRepository;
        this.categoriaResponseAssembler = categoriaResponseAssembler;
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public CategoriaResponse create(@RequestBody @Valid CategoriaRequest request) {

        CategoriaEntity novaCategoria = new CategoriaEntity(request.getNome());
        categoriaRepository.save(novaCategoria);

        CategoriaResponse categoriaResponse = categoriaResponseAssembler.toModel(novaCategoria);
        ResourceUriHelper.addUriInResponseHeader(categoriaResponse.getCategoriaId());

        return categoriaResponse;
    }

    @GetMapping("/{categoriaId}")
    public CategoriaResponse buscaPorId(@PathVariable Long categoriaId) {
        CategoriaEntity categoriaEntity = categoriaRepository.findById(categoriaId).orElseThrow(() ->
                new DomainException(String.format(CATEGORIA_NAO_ENCONTRADA_MSG, categoriaId)));

        return categoriaResponseAssembler.toModel(categoriaEntity);
    }

}
