package br.com.zup.casadocodigo.fechamentocompra;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table(name = "pedido")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private @NotNull long pedidoId;

    @OneToOne(optional = false)
    @JoinColumn(name = "compra_id")
    private @NotNull @Valid CompraEntity compra;

    @OneToMany(mappedBy = "pedido")
    public List<PedidoItensEntity> pedidoItens;

    @Deprecated
    public PedidoEntity() {

    }

    public PedidoEntity(@NotNull @Valid CompraEntity compra, List<PedidoItensEntity> pedidoItens) {
        this.compra = compra;
        this.pedidoItens = pedidoItens;
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public CompraEntity getCompra() {
        return compra;
    }

    public List<PedidoItensEntity> getPedidoItens() {
        return pedidoItens;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PedidoEntity)) return false;
        PedidoEntity that = (PedidoEntity) o;
        return compra.equals(that.compra);
    }

    @Override
    public int hashCode() {
        return Objects.hash(compra);
    }

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "pedidoId=" + pedidoId +
                ", compra=" + compra +
                ", pedidoItens=" + pedidoItens +
                '}';
    }
}
