package br.com.zup.casadocodigo.paisestadocidade;

import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import br.com.zup.casadocodigo.paisestadocidade.repository.EstadoRepository;
import br.com.zup.casadocodigo.paisestadocidade.request.EstadoRequest;
import br.com.zup.casadocodigo.paisestadocidade.response.EstadoResponse;
import br.com.zup.casadocodigo.paisestadocidade.response.EstadoResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/estados" , produces = MediaType.APPLICATION_JSON_VALUE)
public class EstadosController {

    private final EntityManager manager;
    private final EstadoRepository estadoRepository;
    private final EstadoResponseAssembler estadoResponseAssembler;
    private static final String ESTADO_NAO_ENCONTRADA_MSG = "Não existe um cadastro de estado com código %d";
    private static final String ESTADO_NAO_NOME_MSG = "Não existe um cadastro de estado com nome %s";

    public EstadosController(EntityManager manager, EstadoRepository estadoRepository, EstadoResponseAssembler estadoResponseAssembler) {
        this.manager = manager;
        this.estadoRepository = estadoRepository;
        this.estadoResponseAssembler = estadoResponseAssembler;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public EstadoResponse cria(@RequestBody @Valid EstadoRequest estadoRequest) {

        EstadoEntity novaEstado = estadoRequest.toModel(manager);
        novaEstado = estadoRepository.save(novaEstado);

        EstadoResponse estadoResponse = estadoResponseAssembler.toModel(novaEstado);
        ResourceUriHelper.addUriInResponseHeader(novaEstado.getEstadoId());

        return estadoResponse;
    }

    @GetMapping("/{estadoId}")
    public EstadoResponse buscaPorId(@PathVariable Long estadoId) {
        EstadoEntity estado = estadoRepository.findById(estadoId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(ESTADO_NAO_ENCONTRADA_MSG, estadoId)));

        return estadoResponseAssembler.toModel(estado);
    }

    @GetMapping
    public EstadoResponse buscaPorNome(@RequestBody @Valid EstadoRequest estadoRequest) {
        EstadoEntity estado = estadoRepository.findByNome(estadoRequest.getNome()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(ESTADO_NAO_NOME_MSG, estadoRequest.getNome())));

        return estadoResponseAssembler.toModel(estado);
    }
}
