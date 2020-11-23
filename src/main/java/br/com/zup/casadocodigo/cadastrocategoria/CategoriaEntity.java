package br.com.zup.casadocodigo.cadastrocategoria;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Entity
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoriaId;
    private @NotBlank String nome;

    @Deprecated
    public CategoriaEntity() {

    }

    public CategoriaEntity(@NotBlank String nome) {
        this.nome = nome;
    }

    public Long getCategoriaId() {
        return categoriaId;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CategoriaEntity)) return false;
        CategoriaEntity that = (CategoriaEntity) o;
        return nome.equals(that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome);
    }

    @Override
    public String toString() {
        return "CategoriaEntity{" +
                "categoriaId=" + categoriaId +
                ", nome='" + nome + '\'' +
                '}';
    }
}
