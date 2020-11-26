package br.com.zup.casadocodigo.paisestadocidade.request;

import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import br.com.zup.casadocodigo.paisestadocidade.EstadoEntity;
import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class EstadoRequest {

    @NotBlank
    @UniqueValue(domainClass = EstadoEntity.class,fieldName =  "nome")
    private String nome;

    @NotNull
    @ExistsId(domainClass = PaisEntity.class, fieldName = "id")
    private Long paisId;

    public EstadoRequest(@NotBlank String nome, @NotNull @ExistsId(domainClass = PaisEntity.class, fieldName = "id") Long paisId) {
        this.nome = nome;
        this.paisId = paisId;
    }

    public String getNome() {
        return nome;
    }

    public Long getPaisId() {
        return paisId;
    }

    @Override
    public String toString() {
        return "EstadoRequest{" +
                "nome='" + nome + '\'' +
                ", paisId=" + paisId +
                '}';
    }

    public EstadoEntity toModel(EntityManager manager) {
        @NotNull PaisEntity pais = manager.find(PaisEntity.class, paisId);
        Assert.state(pais!=null,"Você esta querendo cadastrar uma estado para um país que nao existe no banco "+paisId);
        return new EstadoEntity(this.nome, pais);
    }
}
