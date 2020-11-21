package br.com.zup.casadocodigo.fechamentocompra;

import br.com.zup.casadocodigo.cadastrocupom.CupomEntity;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Embeddable
public class CupomAplicado {

    @ManyToOne
    private @NotNull CupomEntity cupom;
    private @Positive @NotNull BigDecimal percentualDescontoMomento;
    private @NotNull @Future LocalDate validadeMomento;

    @Deprecated
    public CupomAplicado() {

    }

    public CupomAplicado(@NotNull CupomEntity cupom) {
        this.cupom = cupom;
        this.percentualDescontoMomento = cupom.getPercentDesconto();
        this.validadeMomento = cupom.getValidade();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CupomAplicado)) return false;
        CupomAplicado that = (CupomAplicado) o;
        return cupom.equals(that.cupom);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cupom);
    }

    @Override
    public String toString() {
        return "CupomAplicado{" +
                "cupom=" + cupom +
                ", percentualDescontoMomento=" + percentualDescontoMomento +
                ", validadeMomento=" + validadeMomento +
                '}';
    }
}
