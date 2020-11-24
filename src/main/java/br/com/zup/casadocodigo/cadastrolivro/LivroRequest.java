package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = LivroEntity.class, fieldName = "livroIsbn")
    private String livroIsbn;
    private @NotBlank String titulo;
    private @NotBlank @Max(500) String resumo;
    private @NotBlank @Max(500) String sumario;
    private @NotNull @Positive @Min(20) BigDecimal preco;
    private @Min(100) int nrPaginas;

    @NotNull @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;
    private @NotNull List<@NotNull @Valid AutorEntity> autores;
    private @NotNull @Valid CategoriaEntity categoria;

    public LivroRequest(@NotBlank String livroIsbn, @NotBlank String titulo, @NotBlank String resumo, @NotBlank String sumario, @NotNull @Positive BigDecimal preco, @Min(100) int nrPaginas, @NotNull @Future LocalDate dataPublicacao, @NotNull List<@NotNull @Valid AutorEntity> autores, @NotNull @Valid CategoriaEntity categoria) {
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
}
