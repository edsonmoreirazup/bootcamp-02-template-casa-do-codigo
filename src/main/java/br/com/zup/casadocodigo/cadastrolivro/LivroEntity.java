package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.novoautor.AutorEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Entity
public class LivroEntity {

    @Id
    private @NotBlank String livroIsbn;
    private @NotBlank String titulo;
    private @NotBlank @Max(500) String resumo;
    private @NotBlank @Max(500) String sumario;
    private @NotNull @Positive @Min(20) BigDecimal preco;
    private @Min(100) int nrPaginas;
    private @NotNull @Future LocalDate dataPublicacao;

    @ManyToMany
    @JoinTable(name = "autor_livro",
            joinColumns = @JoinColumn(name = "autor_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_isbn"))
    private @NotNull List<@NotNull @Valid AutorEntity> autores;

    @ManyToOne(optional = false)
    private @NotNull @Valid CategoriaEntity categoria;

    @Deprecated
    public LivroEntity() {

    }

    public LivroEntity(@NotBlank String livroIsbn, @NotBlank String titulo, @NotBlank @Max(500) String resumo, @NotBlank @Max(500) String sumario,
                       @NotNull @Positive @Min(20) BigDecimal preco, @Min(100) int nrPaginas, @NotNull @Future LocalDate dataPublicacao,
                       @NotNull List<@NotNull @Valid AutorEntity> autores, @NotNull @Valid CategoriaEntity categoria) {

        this.livroIsbn = livroIsbn;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.nrPaginas = nrPaginas;
        this.dataPublicacao = dataPublicacao;
        this.autores = autores;
        this.categoria = categoria;
    }

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumo() {

        return resumo;
    }

    public String getSumario() {

        return sumario;
    }

    public BigDecimal getPreco() {

        return preco;
    }

    public int getNrPaginas() {

        return nrPaginas;
    }

    public LocalDate getDataPublicacao() {

        return dataPublicacao;
    }

    public List<AutorEntity> getAutores() {
        return autores;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LivroEntity)) return false;
        LivroEntity that = (LivroEntity) o;
        return livroIsbn.equals(that.livroIsbn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(livroIsbn);
    }

    @Override
    public String toString() {
        return "LivroEntity{" +
                "livroIsbn='" + livroIsbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", nrPaginas=" + nrPaginas +
                ", dataPublicacao=" + dataPublicacao +
                ", autores=" + autores +
                ", categoria=" + categoria +
                '}';
    }
}
