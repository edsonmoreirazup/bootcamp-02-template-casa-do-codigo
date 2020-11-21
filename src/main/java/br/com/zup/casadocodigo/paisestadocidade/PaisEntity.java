package br.com.zup.casadocodigo.paisestadocidade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paisId;
    private @NotBlank String nome;

    @Deprecated
    public PaisEntity() {
    }

    public PaisEntity(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PaisEntity)) return false;
        PaisEntity that = (PaisEntity) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "PaisEntity{" +
                "paisId=" + paisId +
                ", nome='" + nome + '\'' +
                '}';
    }
}
