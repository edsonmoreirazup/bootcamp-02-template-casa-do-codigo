package br.com.zup.casadocodigo.paisestadocidade;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import br.com.zup.casadocodigo.paisestadocidade.repository.PaisRepository;
import br.com.zup.casadocodigo.paisestadocidade.request.PaisRequest;
import br.com.zup.casadocodigo.paisestadocidade.response.PaisResponse;
import br.com.zup.casadocodigo.paisestadocidade.response.PaisResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/paises" , produces = MediaType.APPLICATION_JSON_VALUE)
public class PaisesController {

    private final PaisRepository paisRepository;
    private final PaisResponseAssembler paisResponseAssembler;
    private static final String PAIS_NAO_ENCONTRADA_MSG = "Não existe um cadastro de pais com código %d";
    private static final String PAIS_NAO_NOME_MSG = "Não existe um cadastro de pais com nome %s";

    public PaisesController(PaisRepository paisRepository, PaisResponseAssembler paisResponseAssembler1) {
        this.paisRepository = paisRepository;
        this.paisResponseAssembler = paisResponseAssembler1;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public PaisResponse cria(@RequestBody @Valid PaisRequest paisRequest) {

        PaisEntity novaPais = new PaisEntity(paisRequest.getNome());
        novaPais = paisRepository.save(novaPais);

        PaisResponse paisResponse = paisResponseAssembler.toModel(novaPais);
        ResourceUriHelper.addUriInResponseHeader(novaPais.getPaisId());

        return paisResponse;
    }

    @GetMapping("/{paisId}")
    public PaisResponse buscaPorId(@PathVariable Long paisId) {
        PaisEntity pais = paisRepository.findById(paisId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(PAIS_NAO_ENCONTRADA_MSG, paisId)));

        return paisResponseAssembler.toModel(pais);
    }

    @GetMapping
    public PaisResponse buscaPorNome(@RequestBody @Valid PaisRequest paisRequest) {
        PaisEntity pais = paisRepository.findByNome(paisRequest.getNome()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(PAIS_NAO_NOME_MSG, paisRequest.getNome())));

        return paisResponseAssembler.toModel(pais);
    }
}
