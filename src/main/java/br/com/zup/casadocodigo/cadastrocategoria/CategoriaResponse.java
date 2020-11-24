package br.com.zup.casadocodigo.cadastrocategoria;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "categorias")
public class CategoriaResponse extends RepresentationModel<CategoriaResponse> {

    private Long categoriaId;
    private String nome;

    public Long getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(Long categoriaId) {
        this.categoriaId = categoriaId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
