package br.com.zup.casadocodigo.fechamentocompra;

import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long pedidoId;
    @OneToOne
    private @NotNull @Valid CompraEntity compra;

    @ElementCollection
    private @Size(min = 1) Set<ItemPedido> itens = new HashSet<>();

    @Deprecated
    public PedidoEntity() {
    }

    public PedidoEntity(@NotNull @Valid CompraEntity compra, @Size(min = 1) Set<ItemPedido> itens) {
        Assert.isTrue(!itens.isEmpty(),
                "todo pedido deve ter pelo menos um item");
        this.compra = compra;
        this.itens.addAll(itens);
    }

    public long getPedidoId() {
        return pedidoId;
    }

    public boolean totalIgual(@Positive @NotNull BigDecimal total) {
        BigDecimal totalPedido = itens.stream().map(ItemPedido::total).reduce(BigDecimal.ZERO,
                BigDecimal::add);

        return totalPedido.doubleValue() == total.doubleValue();
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
                "itens=" + itens +
                '}';
    }
}
