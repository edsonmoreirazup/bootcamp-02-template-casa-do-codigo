package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

public class EntidadeEmUsoException extends NegocioException{

    public EntidadeEmUsoException(String mensagem){
        super(mensagem);
    }
}
