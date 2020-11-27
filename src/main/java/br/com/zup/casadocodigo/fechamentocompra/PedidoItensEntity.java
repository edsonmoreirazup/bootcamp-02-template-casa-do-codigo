package br.com.zup.casadocodigo.fechamentocompra;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "pedido_itens")
public class PedidoItensEntity {

    @EmbeddedId
    private PedidoItensEntityId id;

    @MapsId("pedidoId")
    @ManyToOne(optional = false)
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    @MapsId("livroIsbn")
    @ManyToOne(optional = false)
    @JoinColumn(name = "livro_isbn")
    private LivroEntity livro;

    private @NotNull @Positive BigDecimal precoMomento;
    private @Positive int quantidade;

    @Deprecated
    public PedidoItensEntity() {

    }

    public PedidoItensEntity(PedidoEntity pedido, LivroEntity livro, @NotNull @Positive BigDecimal precoMomento,
                             @Positive int quantidade) {
        this.pedido = pedido;
        this.livro = livro;
        this.precoMomento = precoMomento;
        this.quantidade = quantidade;
    }

    public PedidoItensEntityId getId() {
        return id;
    }

    public BigDecimal getPrecoMomento() {
        return precoMomento;
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoItensEntity)) return false;
        PedidoItensEntity that = (PedidoItensEntity) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "PedidoItensEntity{" +
                "id=" + id +
                ", pedido=" + pedido +
                ", livro=" + livro +
                ", precoMomento=" + precoMomento +
                ", quantidade=" + quantidade +
                '}';
    }
}
