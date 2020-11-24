package br.com.zup.casadocodigo.cadastrolivro;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Relation(collectionRelation = "livros")
public class LivroResponse  extends RepresentationModel<LivroResponse> {

    private String livroIsbn;
    private String titulo;
    private String resumo;
    private String sumario;
    private BigDecimal preco;
    private int nrPaginas;
    private LocalDate dataPublicacao;
    private List<AutorEntity> autores;
    private CategoriaEntity categoria;

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getResumo() {
        return resumo;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public int getNrPaginas() {
        return nrPaginas;
    }

    public void setNrPaginas(int nrPaginas) {
        this.nrPaginas = nrPaginas;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public List<AutorEntity> getAutores() {
        return autores;
    }

    public void setAutores(List<AutorEntity> autores) {
        this.autores = autores;
    }

    public CategoriaEntity getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaEntity categoria) {
        this.categoria = categoria;
    }
}
