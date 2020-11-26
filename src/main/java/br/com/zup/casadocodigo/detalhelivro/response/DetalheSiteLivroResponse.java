package br.com.zup.casadocodigo.detalhelivro.response;

import br.com.zup.casadocodigo.cadastrolivro.LivroEntity;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Relation(collectionRelation = "livros")
public class DetalheSiteLivroResponse extends RepresentationModel<DetalheSiteAutorResponse> {

    private List<DetalheSiteAutorResponse> autores;
    private String titulo;
    private String isbn;
    private int numeroPaginas;
    private BigDecimal preco;
    private String resumo;
    private String sumario;
    private String dataPublicacao;

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public List<DetalheSiteAutorResponse> getAutores() {
        return autores;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public String getResumo() {
        return resumo;
    }

    public String getSumario() {
        return sumario;
    }

    public void setAutores(List<DetalheSiteAutorResponse> autores) {
        this.autores = autores;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public void setResumo(String resumo) {
        this.resumo = resumo;
    }

    public void setSumario(String sumario) {
        this.sumario = sumario;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao
                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
}
