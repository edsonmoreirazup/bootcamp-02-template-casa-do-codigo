package br.com.zup.casadocodigo.paisestadocidade;

import br.com.zup.casadocodigo.fechamentocompra.CompraEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "estado")
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estadoId;
    private @NotBlank String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "pais_id", nullable = false)
    private @NotNull @Valid PaisEntity pais;

    @OneToMany(mappedBy = "estado")
    private List<CidadeEntity> cidades;

    @Deprecated
    public EstadoEntity() {

    }

    public EstadoEntity(@NotBlank String nome, @NotNull @Valid PaisEntity pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public Integer getEstadoId() {
        return estadoId;
    }

    public String getNome() {
        return nome;
    }

    public PaisEntity getPais() {
        return pais;
    }

    public List<CidadeEntity> getCidades() {
        return cidades;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EstadoEntity)) return false;
        EstadoEntity that = (EstadoEntity) o;
        return nome.equals(that.nome) &&
                pais.equals(that.pais);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, pais);
    }

    @Override
    public String toString() {
        return "EstadoEntity{" +
                "estadoId=" + estadoId +
                ", nome='" + nome + '\'' +
                ", pais=" + pais +
                '}';
    }
}
