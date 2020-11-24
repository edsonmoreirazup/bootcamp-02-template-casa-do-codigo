package br.com.zup.casadocodigo.novoautor;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
public class AutorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long autorId;
    private @NotBlank String nome;
    private @NotBlank @Email String email;
    private @NotBlank @Size(max = 400) String descricao;
    private LocalDateTime dataRegistro = LocalDateTime.now();

    @ManyToMany(mappedBy = "autores")
    private List<LivroEntity> livros;

    @Deprecated
    public AutorEntity() {

    }

    public AutorEntity(@NotBlank String nome, @NotBlank @Email String email, @NotBlank @Size(max = 400) String descricao, List<LivroEntity> livros) {
        this.nome = nome;
        this.email = email;
        this.descricao = descricao;
        this.livros = livros;
    }

    public long getAutorId() {
        return autorId;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AutorEntity)) return false;
        AutorEntity that = (AutorEntity) o;
        return email.equals(that.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "AutorEntity{" +
                "autorId=" + autorId +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataRegistro=" + dataRegistro +
                '}';
    }
}