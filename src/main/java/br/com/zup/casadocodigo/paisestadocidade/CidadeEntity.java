package br.com.zup.casadocodigo.paisestadocidade;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.fechamentocompra.CompraEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "cidade")
public class CidadeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cidadeId;
    private @NotBlank String nome;

    @ManyToOne(optional = false)
    @JoinColumn(name = "estado_id", nullable = false)
    private @NotNull @Valid EstadoEntity estado;

    @OneToMany(mappedBy = "cidade")
    private List<CompraEntity> compras;

    @Deprecated
    public CidadeEntity(){

    }

    public CidadeEntity(@NotBlank String nome, @NotNull @Valid EstadoEntity estadoByEstado) {
        this.nome = nome;
        this.estado = estadoByEstado;
    }

    public Long getCidadeId() {
        return cidadeId;
    }

    public String getNome() {
        return nome;
    }

    public List<CompraEntity> getCompras() {
        return compras;
    }

    public EstadoEntity getEstado() {
        return estado;
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
