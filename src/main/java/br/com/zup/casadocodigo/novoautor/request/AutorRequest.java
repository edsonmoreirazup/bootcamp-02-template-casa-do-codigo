package br.com.zup.casadocodigo.novoautor.request;

import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class AutorRequest {

    private @NotBlank String nome;

    @NotBlank @Email
    @UniqueValue(domainClass = AutorEntity.class,fieldName = "email")
    private  String email;
    private @NotBlank @Size(max = 400) String descricao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
    }

    public AutorEntity toModel() {
        return new AutorEntity(this.nome,this.email,this.descricao);
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "AutorRequest{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
