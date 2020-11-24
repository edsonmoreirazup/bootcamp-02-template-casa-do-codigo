package br.com.zup.casadocodigo.novoautor.request;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;

public class AutorRequest {

    private @NotBlank String nome;

    @NotBlank @Email
    @UniqueValue(domainClass = AutorEntity.class,fieldName = "email")
    private  String email;
    private @NotBlank @Size(max = 400) String descricao;
    private @NotNull LocalDateTime dataRegistro;
    private List<LivroEntity> livros;

    public AutorRequest(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao, @NotNull LocalDateTime dataRegistro, List<LivroEntity> livros) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.dataRegistro = dataRegistro;
        this.livros = livros;
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

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public List<LivroEntity> getLivros() {
        return livros;
    }
}
