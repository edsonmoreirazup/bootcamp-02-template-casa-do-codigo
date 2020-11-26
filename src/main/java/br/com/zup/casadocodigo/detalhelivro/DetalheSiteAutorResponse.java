package br.com.zup.casadocodigo.detalhelivro;

import br.com.zup.casadocodigo.cadastrolivro.LivroResponse;
import br.com.zup.casadocodigo.novoautor.AutorEntity;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

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
