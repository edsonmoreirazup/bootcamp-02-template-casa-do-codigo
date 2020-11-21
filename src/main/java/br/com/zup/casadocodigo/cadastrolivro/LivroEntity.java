package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.novoautor.AutorEntity;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class LivroEntity {

    @Id
    private @NotBlank String livroIsbn;
    private @NotBlank String titulo;
    private @NotBlank String resumo;
    private @NotBlank String sumario;
    private @NotNull @Positive BigDecimal preco;
    private @Min(100) int nrPaginas;
    private @NotNull @Future LocalDate dataPublicacao;
    @ManyToOne
    private @NotNull @Valid AutorEntity autor;
    @ManyToOne
    private @NotNull @Valid CategoriaEntity categoria;

    @Deprecated
    public LivroEntity() {

    }

    public LivroEntity(@NotBlank String livroIsbn, @NotBlank String titulo, @NotBlank String resumo, @NotBlank String sumario, @NotNull @Positive BigDecimal preco, @Min(100) int nrPaginas, @NotNull @Future LocalDate dataPublicacao, @NotNull @Valid AutorEntity autor, @NotNull @Valid CategoriaEntity categoria) {
        this.livroIsbn = livroIsbn;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.nrPaginas = nrPaginas;
        this.dataPublicacao = dataPublicacao;
        this.autor = autor;
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

    public AutorEntity getAutor() {

        return autor;
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
                ", autor=" + autor +
                ", categoria=" + categoria +
                '}';
    }
}
