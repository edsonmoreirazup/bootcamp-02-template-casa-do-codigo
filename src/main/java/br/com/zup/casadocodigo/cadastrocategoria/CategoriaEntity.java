package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "categoria")
public class CategoriaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "categoria_id")
    private Long categoriaId;
    private @NotBlank String nome;

    @OneToMany(mappedBy = "categoria")
    private List<LivroEntity> livros;

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

    public List<LivroEntity> getLivros() {
        return livros;
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
