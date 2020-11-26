package br.com.zup.casadocodigo.paisestadocidade;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "pais")
public class PaisEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paisId;
    private @NotBlank String nome;

    @OneToMany(mappedBy = "pais")
    private List<EstadoEntity> estados;

    @Deprecated
    public PaisEntity() {
    }

    public PaisEntity(@NotBlank String nome) {
        this.nome = nome;
    }

    public Integer getPaisId() {
        return paisId;
    }

    public String getNome() {
        return nome;
    }

    public List<EstadoEntity> getEstados() {
        return estados;
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
