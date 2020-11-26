package br.com.zup.casadocodigo.paisestadocidade;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.compartilhado.ResourceUriHelper;
import br.com.zup.casadocodigo.compartilhado.exceptionhandler.EntidadeNaoEncontradaException;
import br.com.zup.casadocodigo.paisestadocidade.repository.CidadeRepository;
import br.com.zup.casadocodigo.paisestadocidade.request.CidadeRequest;
import br.com.zup.casadocodigo.paisestadocidade.response.CidadeResponse;
import br.com.zup.casadocodigo.paisestadocidade.response.CidadeResponseAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.validation.Valid;

@RestController
@RequestMapping(path = "/cidades" , produces = MediaType.APPLICATION_JSON_VALUE)
public class CidadesController {

    private final EntityManager manager;
    private final CidadeRepository cidadeRepository;
    private final CidadeResponseAssembler cidadeResponseAssembler;
    private static final String CIDADE_NAO_ENCONTRADA_MSG = "Não existe um cadastro de cidade com código %d";
    private static final String CIDADE_NAO_NOME_MSG = "Não existe um cadastro de cidade com nome %s";

    public CidadesController(EntityManager manager, CidadeRepository cidadeRepository, CidadeResponseAssembler cidadeResponseAssembler) {
        this.manager = manager;
        this.cidadeRepository = cidadeRepository;
        this.cidadeResponseAssembler = cidadeResponseAssembler;
    }

    @Transactional
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public CidadeResponse cria(@RequestBody @Valid CidadeRequest cidadeRequest) {

        CidadeEntity novaCidade = cidadeRequest.toModel(manager);
        novaCidade = cidadeRepository.save(novaCidade);

        CidadeResponse cidadeResponse = cidadeResponseAssembler.toModel(novaCidade);
        ResourceUriHelper.addUriInResponseHeader(novaCidade.getCidadeId());

        return cidadeResponse;
    }

    @GetMapping("/{cidadeId}")
    public CidadeResponse buscaPorId(@PathVariable Long cidadeId) {
        CidadeEntity cidade = cidadeRepository.findById(cidadeId).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_ENCONTRADA_MSG, cidadeId)));

        return cidadeResponseAssembler.toModel(cidade);
    }

    @GetMapping
    public CidadeResponse buscaPorNome(@RequestBody @Valid CidadeRequest cidadeRequest) {
        CidadeEntity cidade = cidadeRepository.findByNome(cidadeRequest.getNome()).orElseThrow(() ->
                new EntidadeNaoEncontradaException(String.format(CIDADE_NAO_NOME_MSG, cidadeRequest.getNome())));

        return cidadeResponseAssembler.toModel(cidade);
    }
}
