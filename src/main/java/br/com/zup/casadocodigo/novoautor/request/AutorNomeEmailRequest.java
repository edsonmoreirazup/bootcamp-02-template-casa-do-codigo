package br.com.zup.casadocodigo.novoautor.request;

import br.com.zup.casadocodigo.cadastrolivro.LivroRequest;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class AutorNomeEmailRequest {

    private @NotBlank String nome;

    @NotBlank @Email
    @UniqueValue(domainClass = AutorEntity.class,fieldName = "email")
    private  String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AutorNomeEmailRequest(@NotBlank String nome, @NotBlank @Email String email) {
        this.nome = nome;
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }
}
