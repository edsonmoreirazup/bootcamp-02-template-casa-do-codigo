package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.compartilhado.UniqueValue;

import javax.validation.constraints.NotBlank;

public class CategoriaRequest {

    @NotBlank
    @UniqueValue(domainClass = CategoriaEntity.class, fieldName = "nome")
    private String nome;

    public CategoriaRequest(@NotBlank String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
