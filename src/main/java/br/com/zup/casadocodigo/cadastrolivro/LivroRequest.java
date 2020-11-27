package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.compartilhado.ExistsId;
import br.com.zup.casadocodigo.compartilhado.UniqueValue;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class LivroRequest {

    @NotBlank
    @UniqueValue(domainClass = LivroEntity.class, fieldName = "livroIsbn")
    private String livroIsbn;

    @UniqueValue(domainClass = LivroEntity.class, fieldName = "titulo")
    private @NotBlank String titulo;
    private @NotBlank @Max(500) String resumo;
    private @NotBlank @Max(500) String sumario;
    private @NotNull @Positive @Min(20) BigDecimal preco;
    private @NotNull @Positive @Min(100) int nrPaginas;

    @NotNull @Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private LocalDate dataPublicacao;

    private @NotNull @Size(min=1) List<@NotNull @ExistsId(domainClass = AutorEntity.class, fieldName = "id") Long> idAutores;
    private @NotNull Long idCategoria;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public LivroRequest(@NotBlank String livroIsbn, @NotBlank String titulo, @NotBlank @Max(500) String resumo,
                        @NotBlank @Max(500) String sumario, @NotNull @Positive @Min(20) BigDecimal preco,
                        @NotNull @Positive @Min(100) int nrPaginas, @NotNull @Future LocalDate dataPublicacao,
                        @NotNull @Size(min = 1) List<@NotNull @ExistsId(domainClass = AutorEntity.class, fieldName = "id") Long> idAutores,
                        @NotNull Long idCategoria) {

        this.livroIsbn = livroIsbn;
        this.titulo = titulo;
        this.resumo = resumo;
        this.sumario = sumario;
        this.preco = preco;
        this.nrPaginas = nrPaginas;
        this.dataPublicacao = dataPublicacao;
        this.idAutores = idAutores;
        this.idCategoria = idCategoria;
    }

    public LivroEntity toModel(EntityManager manager) {

        TypedQuery<AutorEntity> query = manager.createQuery("SELECT a FROM AutorEntity a WHERE a.autorId in :idAutores", AutorEntity.class);
        query.setParameter("idAutores", idAutores);
        List<@NotNull @Valid AutorEntity> autores = query.getResultList();

        @NotNull CategoriaEntity categoria = manager.find(CategoriaEntity.class, idCategoria);

        Assert.state(autores!=null,"Você esta querendo cadastrar um livro para um autor que nao existe no banco ");
        Assert.state(categoria!=null,"Você esta querendo cadastrar um livro para uma categoria que nao existe no banco "+idCategoria);

        return new LivroEntity(this.livroIsbn, this.titulo, this.resumo, this.sumario, this.preco, this.nrPaginas, this.dataPublicacao,
                autores, categoria);
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

    public List<Long> getIdAutores() {
        return idAutores;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    @Override
    public String toString() {
        return "LivroRequest{" +
                "livroIsbn='" + livroIsbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", resumo='" + resumo + '\'' +
                ", sumario='" + sumario + '\'' +
                ", preco=" + preco +
                ", nrPaginas=" + nrPaginas +
                ", dataPublicacao=" + dataPublicacao +
                ", idAutores=" + idAutores +
                ", idCategoria=" + idCategoria +
                '}';
    }
}
