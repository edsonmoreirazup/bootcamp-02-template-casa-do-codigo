package br.com.zup.casadocodigo.fechamentocompra;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Objects;

@Embeddable
public class ItemPedido {

    @ManyToOne
    private @NotNull LivroEntity livro;
    private @NotNull @Positive int quantidade;
    private @NotNull @Positive BigDecimal precoMomento;

    @Deprecated
    public ItemPedido() {

    }

    public ItemPedido(@NotNull LivroEntity livro, @NotNull @Positive int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
        this.precoMomento = livro.getPreco();
    }

    public BigDecimal total() {
        return precoMomento.multiply(new BigDecimal(quantidade));
    }

    public int getQuantidade() {
        return quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ItemPedido)) return false;
        ItemPedido that = (ItemPedido) o;
        return livro.equals(that.livro);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livro);
    }

    @Override
    public String toString() {
        return "ItemPedido{" +
                "livro=" + livro +
                ", quantidade=" + quantidade +
                ", precoMomento=" + precoMomento +
                '}';
    }
}
