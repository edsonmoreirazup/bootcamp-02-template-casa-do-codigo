package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    public static final String MSG_ERRO_GENERICA_USUARIO_FINAL
            = "Ocorreu um erro interno inesperado no sistema. Tente novamente e se "
            + "o problema persistir, entre em contato com o administrador do sistema.";

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(DomainException.class)
    public ResponseEntity<?> handleDomain(DomainException ex, WebRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        TipoErro tipoErro = TipoErro.ERRO_NEGOCIO;
        String detail = ex.getMessage();

        Erro erro = createErrorBuilder(status, tipoErro, detail)
                .withUserMessage(detail)
                .build();

        return handleExceptionInternal(ex, erro, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {
        if (body == null) {
            body = Erro.builder()
                    .withTimestamp(OffsetDateTime.now())
                    .withTitle(status.getReasonPhrase())
                    .withStatus(status.value())
                    .withUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        } else if (body instanceof String) {
            body = Erro.builder()
                    .withTimestamp(OffsetDateTime.now())
                    .withTitle((String) body)
                    .withStatus(status.value())
                    .withUserMessage(MSG_ERRO_GENERICA_USUARIO_FINAL)
                    .build();
        }

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    private Erro.ErroBuilder createErrorBuilder(HttpStatus status,
                                                     TipoErro tipoErro, String detail) {

        return Erro.builder()
                .withTimestamp(OffsetDateTime.now())
                .withStatus(status.value())
                .withType(tipoErro.getUri())
                .withTitle(tipoErro.getTitle())
                .withDetail(detail);
    }
}
