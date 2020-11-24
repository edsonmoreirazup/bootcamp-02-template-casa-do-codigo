package br.com.zup.casadocodigo.novoautor.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class AutorEmailRequest {

    private @NotBlank @Email String email;

    public AutorEmailRequest(@NotBlank @Email String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
