package br.com.zup.casadocodigo.novoautor;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import br.com.zup.casadocodigo.novoautor.request.AutorEmailRequest;
import br.com.zup.casadocodigo.novoautor.request.AutorRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/autores" , produces = MediaType.APPLICATION_JSON_VALUE)
public class AutoresController {

    private final AutorRepository autorRepository;
    private final AutorResponseAssembler autorResponseAssembler;
    private static final String AUTOR_NAO_ENCONTRADO_ID_MSG = "Não existe um cadastro de autor com código %d";
    private static final String AUTOR_NAO_ENCONTRADO_EMAIL_MSG = "Não existe um cadastro de autor com email %s";

    public AutoresController(AutorRepository autorRepository, AutorResponseAssembler autorResponseAssembler) {
        this.autorRepository = autorRepository;
        this.autorResponseAssembler = autorResponseAssembler;
    }

    @Transactional
    @ResponseStatus(HttpStatus.OK)
    public AutorResponse cria(@RequestBody @Valid AutorRequest request) {

        AutorEntity novoAutor = new AutorEntity(request.getNome(),request.getEmail(), request.getDescricao(), request.getLivros());
        autorRepository.save(novoAutor);

        AutorResponse autorResponse = autorResponseAssembler.toModel(novoAutor);
        ResourceUriHelper.addUriInResponseHeader(autorResponse.getAutorId());

        return autorResponse;
    }

    @GetMapping("/{autorId}")
    public AutorResponse buscaPorId(@PathVariable Long autorId) {
        AutorEntity autorEntity = autorRepository.findById(autorId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADO_ID_MSG, autorId)));

        return autorResponseAssembler.toModel(autorEntity);
    }

    @GetMapping
    public AutorResponse buscaPorEmail(@RequestBody @Valid AutorEmailRequest request) {
        AutorEntity autorEntity = autorRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADO_EMAIL_MSG, request.getEmail())));

        return autorResponseAssembler.toModel(autorEntity);
    }
}
