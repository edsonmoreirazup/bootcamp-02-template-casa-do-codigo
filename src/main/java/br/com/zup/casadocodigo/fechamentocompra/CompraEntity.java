package br.com.zup.casadocodigo.fechamentocompra;

import br.com.zup.casadocodigo.cadastrocupom.CupomEntity;
import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import org.springframework.util.Assert;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;
import java.util.function.Function;

@Entity
public class CompraEntity {

    @Id
    @GeneratedValue
    @Column(columnDefinition = "BINARY(16)")
    private UUID compraId;
    private @NotBlank String cpfCnpj;
    private @Email @NotBlank String email;
    private @NotBlank String nome;
    private @NotBlank String sobrenome;
    private @NotBlank String endereco;
    private @NotBlank String complemento;
    private @NotBlank String telefone;
    private @NotBlank String cep;
    private @NotNull BigDecimal valorPago;
    @OneToOne(mappedBy = "compra", cascade = CascadeType.PERSIST, optional = false)
    private @NotNull @Valid PedidoEntity pedido;
    @ManyToOne
    private @NotNull @Valid CidadeEntity cidade;
    @Embedded
    private CupomAplicado cupomAplicado;

    @Deprecated
    public CompraEntity() {
    }

    public CompraEntity(@NotBlank String cpfCnpj, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String telefone, @NotBlank String cep, @NotNull BigDecimal valorPago, Function<CompraEntity, PedidoEntity> funcaoCriacaoPedido, @NotNull @Valid CidadeEntity cidade) {
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cep = cep;
        this.valorPago = valorPago;
        this.pedido = funcaoCriacaoPedido.apply(this);
        this.cidade = cidade;
    }

    public UUID getCompraId() {
        return compraId;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getEndereco() {
        return endereco;
    }

    public String getComplemento() {
        return complemento;
    }

    public String getTelefone() {
        return telefone;
    }

    public String getCep() {
        return cep;
    }

    public BigDecimal getValorPago() {
        return valorPago;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public CidadeEntity getCidade() {
        return cidade;
    }

    public CupomAplicado getCupomAplicado() {
        return cupomAplicado;
    }

    public void aplicaCupom(CupomEntity cupom) {
        Assert.isTrue(cupom.valido(),"Olha o cupom que está sendo aplicado não está mais válido");
        Assert.isNull(cupomAplicado,"Olha você não pode trocar um cupom de uma compra");
        this.cupomAplicado = new CupomAplicado(cupom);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompraEntity)) return false;
        CompraEntity that = (CompraEntity) o;
        return pedido.equals(that.pedido);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pedido);
    }

    @Override
    public String toString() {
        return "CompraEntity{" +
                "compraId=" + compraId +
                ", cpfCnpj='" + cpfCnpj + '\'' +
                ", email='" + email + '\'' +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", complemento='" + complemento + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cep='" + cep + '\'' +
                ", valorPago=" + valorPago +
                ", pedido=" + pedido +
                ", cidade=" + cidade +
                ", cupomAplicado=" + cupomAplicado +
                '}';
    }

}
