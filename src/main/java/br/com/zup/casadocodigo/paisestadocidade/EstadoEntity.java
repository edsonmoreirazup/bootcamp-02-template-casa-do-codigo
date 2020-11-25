package br.com.zup.casadocodigo.paisestadocidade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class EstadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer estadoId;
    private @NotBlank String nome;
    @ManyToOne(optional = false)
    private @NotNull @Valid PaisEntity pais;

    @Deprecated
    public EstadoEntity() {

    }

    public EstadoEntity(@NotBlank String nome, @NotNull @Valid PaisEntity pais) {
        this.nome = nome;
        this.pais = pais;
    }

    public String getNome() {
        return nome;
    }

    public PaisEntity getPais() {
        return pais;
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
