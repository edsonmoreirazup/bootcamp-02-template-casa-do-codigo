package br.com.zup.casadocodigo.novoautor;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import br.com.zup.casadocodigo.novoautor.request.AutorEmailRequest;
import br.com.zup.casadocodigo.novoautor.request.AutorNomeEmailRequest;
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
    private static final String AUTOR_NAO_ENCONTRADO_NOME_EMAIL_MSG = "Não existe um cadastro de autor com nome %s e email %s";

    public AutoresController(AutorRepository autorRepository, AutorResponseAssembler autorResponseAssembler) {
        this.autorRepository = autorRepository;
        this.autorResponseAssembler = autorResponseAssembler;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public AutorResponse cria(@RequestBody @Valid AutorRequest autorRequest) {

        AutorEntity novoAutor = autorRequest.toModel();
        novoAutor = autorRepository.save(novoAutor);

        AutorResponse autorResponse = autorResponseAssembler.toModel(novoAutor);
        ResourceUriHelper.addUriInResponseHeader(novoAutor.getAutorId());

        return autorResponse;
    }

    @GetMapping("/{autorId}")
    public AutorResponse buscaPorId(@PathVariable Long autorId) {
        AutorEntity autorEntity = autorRepository.findById(autorId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADO_ID_MSG, autorId)));

        return autorResponseAssembler.toModel(autorEntity);
    }

    @GetMapping(path = "/busca_por_email")
    public AutorResponse buscaPorEmail(@RequestBody @Valid AutorEmailRequest request) {
        AutorEntity autorEntity = autorRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADO_EMAIL_MSG, request.getEmail())));

        return autorResponseAssembler.toModel(autorEntity);
    }

    @GetMapping(path = "/busca_por_nome_email")
    public AutorResponse buscaPorNomeAndEmail(@RequestBody @Valid AutorNomeEmailRequest request) {
        AutorEntity autorEntity = autorRepository.findByNomeAndEmail(request.getNome(), request.getEmail()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(AUTOR_NAO_ENCONTRADO_NOME_EMAIL_MSG, request.getNome(), request.getEmail())));

        return autorResponseAssembler.toModel(autorEntity);
    }
}
