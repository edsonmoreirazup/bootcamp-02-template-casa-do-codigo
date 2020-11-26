package br.com.zup.casadocodigo.paisestadocidade.response;

import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Relation(collectionRelation = "estados")
public class EstadoResponse extends RepresentationModel<EstadoResponse> {

    private Integer estadoId;
    private String nome;
    private Integer paisId;

    public Integer getEstadoId() {
        return estadoId;
    }

    public void setEstadoId(Integer estadoId) {
        this.estadoId = estadoId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public void setPaisId(Integer paisId) {
        this.paisId = paisId;
    }
}
