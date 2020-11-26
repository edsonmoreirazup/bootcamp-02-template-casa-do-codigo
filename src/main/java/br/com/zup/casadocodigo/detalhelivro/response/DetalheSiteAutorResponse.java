package br.com.zup.casadocodigo.detalhelivro.response;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;

@Relation(collectionRelation = "autores")
public class DetalheSiteAutorResponse extends RepresentationModel<DetalheSiteAutorResponse> {

    private String nome;
    private String descricao;

    public DetalheSiteAutorResponse(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

}
