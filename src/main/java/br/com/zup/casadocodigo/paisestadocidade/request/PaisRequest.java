package br.com.zup.casadocodigo.paisestadocidade.request;

import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;

import javax.validation.constraints.NotBlank;

public class PaisRequest {

    @NotBlank
    @UniqueValue(domainClass = PaisEntity.class,fieldName =  "nome")
    private String nome;

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
