package br.com.zup.casadocodigo.compartilhado;

import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.net.URI;
import java.util.Objects;

public final class ResourceUriHelper {

    private ResourceUriHelper(){
        throw new UnsupportedOperationException("É uma classe utilitária e não pode ser instanciada");
    }

    public static void addUriInResponseHeader(Object resourceId){
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(resourceId).toUri();

        HttpServletResponse response = ((ServletRequestAttributes)
                Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getResponse();

        Objects.requireNonNull(response).setHeader(HttpHeaders.LOCATION, uri.toString());
    }
}
