package br.com.zup.casadocodigo.fechamentocompra.request;

import br.com.zup.casadocodigo.cadastrocupom.CupomEntity;
import br.com.zup.casadocodigo.cadastrocupom.CupomRepository;
import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.fechamentocompra.CompraEntity;
import br.com.zup.casadocodigo.fechamentocompra.PedidoEntity;
import br.com.zup.casadocodigo.fechamentocompra.validation.CompraGroupSequenceProvider;
import br.com.zup.casadocodigo.fechamentocompra.validation.Groups.PessoaFisicaCpf;
import br.com.zup.casadocodigo.fechamentocompra.validation.Groups.PessoaJuridicaCnpj;
import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;
import org.hibernate.validator.group.GroupSequenceProvider;

import javax.persistence.EntityManager;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@GroupSequenceProvider(CompraGroupSequenceProvider.class)
public class CompraRequest {

    @NotBlank
    @CPF(groups = PessoaFisicaCpf.class)
    @CNPJ(groups = PessoaJuridicaCnpj.class)
    private String cpfCnpj;
    private @Email @NotBlank String email;
    private @NotBlank String nome;
    private @NotBlank String sobrenome;
    private @NotBlank String endereco;
    private @NotBlank String complemento;
    private @NotBlank String telefone;
    private @NotBlank String cep;
    private @NotNull BigDecimal valorPago;

    @NotNull
    @ExistsId(domainClass = CidadeEntity.class, fieldName = "cidadeId")
    private Long cidadeId;

    private PedidoRequest pedido;
    @ExistsId(domainClass = CupomEntity.class, fieldName = "codigo")
    private String codigoCupom;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CompraRequest(@NotBlank @CPF(groups = PessoaFisicaCpf.class) @CNPJ(groups = PessoaJuridicaCnpj.class) String cpfCnpj, @Email @NotBlank String email, @NotBlank String nome, @NotBlank String sobrenome, @NotBlank String endereco, @NotBlank String complemento, @NotBlank String telefone, @NotBlank String cep, @NotNull BigDecimal valorPago, @NotNull @ExistsId(domainClass = CidadeEntity.class, fieldName = "cidadeId") Long cidadeId, PedidoRequest pedido, @ExistsId(domainClass = CupomEntity.class, fieldName = "codigo") String codigoCupom) {
        this.cpfCnpj = cpfCnpj;
        this.email = email;
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.endereco = endereco;
        this.complemento = complemento;
        this.telefone = telefone;
        this.cep = cep;
        this.valorPago = valorPago;
        this.cidadeId = cidadeId;
        this.pedido = pedido;
        this.codigoCupom = codigoCupom;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public CompraEntity toModel(EntityManager manager) {
      return null;
    }
}
