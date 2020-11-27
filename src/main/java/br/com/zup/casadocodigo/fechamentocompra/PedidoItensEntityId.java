package br.com.zup.casadocodigo.fechamentocompra;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PedidoItensEntityId implements Serializable {

    private Long pedidoId;
    private String livroIsbn;

    @Deprecated
    public PedidoItensEntityId(){

    }

    public Long getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(Long pedidoId) {
        this.pedidoId = pedidoId;
    }

    public String getLivroIsbn() {
        return livroIsbn;
    }

    public void setLivroIsbn(String livroIsbn) {
        this.livroIsbn = livroIsbn;
    }
}
