package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

public class DomainException extends RuntimeException {

    public DomainException(String mensagem) {
        super(mensagem);
    }

    public DomainException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
