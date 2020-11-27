package br.com.zup.casadocodigo.paisestadocidade.request;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import br.com.zup.casadocodigo.paisestadocidade.EstadoEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class CidadeRequest {

    @NotBlank
    @UniqueValue(domainClass = CidadeEntity.class,fieldName =  "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = EstadoEntity.class, fieldName = "id")
    private Long estadoId;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CidadeRequest(@NotBlank String nome, @NotNull Long estadoId) {
        this.nome = nome;
        this.estadoId = estadoId;
    }

    public String getNome() {
        return nome;
    }

    public Long getEstadoId() {
        return estadoId;
    }

    public CidadeEntity toModel(EntityManager manager) {
        @NotNull EstadoEntity estado = manager.find(EstadoEntity.class, estadoId);
        Assert.state(estado!=null,"Você esta querendo cadastrar uma cidade para um estado que nao existe no banco "+estadoId);
        return new CidadeEntity(this.nome, estado);
    }
}
