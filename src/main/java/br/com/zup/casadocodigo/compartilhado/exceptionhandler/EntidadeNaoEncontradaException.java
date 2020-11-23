package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

public class EntidadeNaoEncontradaException extends NegocioException {

    public EntidadeNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
