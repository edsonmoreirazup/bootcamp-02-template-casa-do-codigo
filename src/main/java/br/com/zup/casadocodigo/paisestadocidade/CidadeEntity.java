package br.com.zup.casadocodigo.paisestadocidade;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidadeId;
    private @NotBlank String nome;
    @ManyToOne
    private @NotNull @Valid EstadoEntity estado;

    @Deprecated
    public CidadeEntity(){

    }

    public CidadeEntity(@NotBlank String nome, @NotNull @Valid EstadoEntity estadoByEstado) {
        this.nome = nome;
        this.estado = estadoByEstado;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CidadeEntity)) return false;
        CidadeEntity that = (CidadeEntity) o;
        return nome.equals(that.nome) &&
                estado.equals(that.estado);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, estado);
    }

    @Override
    public String toString() {
        return "CidadeEntity{" +
                "cidadeId=" + cidadeId +
                ", nome='" + nome + '\'' +
                ", estado=" + estado +
                '}';
    }
}
