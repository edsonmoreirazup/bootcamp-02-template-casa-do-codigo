package br.com.zup.casadocodigo.novoautor.request;

import br.com.zup.casadocodigo.novoautor.AutorEntity;
import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorEmailRequest {

    private @NotBlank @Email String email;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AutorEmailRequest(@NotBlank @Email String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
