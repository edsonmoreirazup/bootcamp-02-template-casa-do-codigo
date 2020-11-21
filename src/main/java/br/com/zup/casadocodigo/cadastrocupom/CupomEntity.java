package br.com.zup.casadocodigo.cadastrocupom;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
public class CupomEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID cupomId;
    private @NotBlank String codigo;
    private @Positive @NotNull BigDecimal percentDesconto;
    private @Future @NotNull LocalDate validade;

    @Deprecated
    public CupomEntity() {

    }

    public CupomEntity(@NotBlank String codigo, @Positive @NotNull BigDecimal percentDesconto, @Future @NotNull LocalDate validade) {
        this.codigo = codigo;
        this.percentDesconto = percentDesconto;
        this.validade = validade;
    }

    public UUID getCupomId() {
        return cupomId;
    }

    public String getCodigo() {
        return codigo;
    }

    public BigDecimal getPercentDesconto() {
        return percentDesconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public boolean valido() {
        return LocalDate.now().compareTo(this.validade) <= 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CupomEntity)) return false;
        CupomEntity that = (CupomEntity) o;
        return codigo.equals(that.codigo) &&
                validade.equals(that.validade);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo, validade);
    }

    @Override
    public String toString() {
        return "CupomEntity{" +
                "cupomId=" + cupomId +
                ", codigo='" + codigo + '\'' +
                ", percentDesconto=" + percentDesconto +
                ", validade=" + validade +
                '}';
    }
}
