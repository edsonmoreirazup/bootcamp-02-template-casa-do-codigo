package br.com.zup.casadocodigo.novoautor;

import br.com.zup.casadocodigo.cadastrocategoria.*;
import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/autores" , produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoresController {

    private AutorRepository autorRepository;
    private AutorResponseAssembler autorResponseAssembler;
    private static final String AUTOR_NAO_ENCONTRADA_MSG = "Não existe um cadastro de autor com código %d";

    public AutoresController(AutorRepository autorRepository, AutorResponseAssembler autorResponseAssembler) {
        this.autorRepository = autorRepository;
        this.autorResponseAssembler = autorResponseAssembler;
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public AutorResponse create(@RequestBody @Valid AutorRequest request) {

        AutorEntity novoAutor = new AutorEntity(request.getNome(),request.getEmail(), request.getDescricao());
        autorRepository.save(novoAutor);

        AutorResponse autorResponse = autorResponseAssembler.toModel(novoAutor);
        ResourceUriHelper.addUriInResponseHeader(autorResponse.getAutorId());

        return autorResponse;
    }

    @GetMapping("/{autorId}")
    public AutorResponse buscaPorId(@PathVariable Long autorId) {
        AutorEntity categoriaEntity = autorRepository.findById(autorId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADA_MSG, autorId)));

        return autorResponseAssembler.toModel(categoriaEntity);
    }
}
