package br.com.zup.casadocodigo.fechamentocompra.validation;

import br.com.zup.casadocodigo.fechamentocompra.request.CompraRequest;
import org.hibernate.validator.spi.group.DefaultGroupSequenceProvider;

import java.util.ArrayList;
import java.util.List;

public class CompraGroupSequenceProvider implements DefaultGroupSequenceProvider<CompraRequest> {

    @Override
    public List<Class<?>> getValidationGroups(CompraRequest compraRequest) {
        List<Class<?>> groups = new ArrayList<>();
        groups.add(CompraRequest.class);

        if (compraRequest != null && compraRequest.getCpfCnpj() != null) {
            int quantidadeAlgarismos =  compraRequest.getCpfCnpj().length();
            if (quantidadeAlgarismos > 11) {
                groups.add(Groups.PessoaJuridicaCnpj.class);
            } else {
                groups.add(Groups.PessoaFisicaCpf.class);
            }
        }
        return groups;
    }
}
