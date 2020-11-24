package br.com.zup.casadocodigo.cadastrocategoria;


import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "categorias")
public class CategoriaResponse extends RepresentationModel<CategoriaResponse> {

    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
