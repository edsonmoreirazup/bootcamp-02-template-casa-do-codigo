package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import java.time.OffsetDateTime;
import java.util.List;

@JsonInclude(Include.NON_NULL)
public class Erro {

    private Integer status;
    private OffsetDateTime timestamp;
    private String type;
    private String title;
    private String detail;
    private String userMessage;
    private List<Object> objects;

    public Erro(Integer status, OffsetDateTime timestamp, String type, String title, String detail, String userMessage, List<Object> objects) {
        this.status = status;
        this.timestamp = timestamp;
        this.type = type;
        this.title = title;
        this.detail = detail;
        this.userMessage = userMessage;
        this.objects = objects;
    }

    private Erro(ErroBuilder erroBuilder) {
        this.status = erroBuilder.status;
        this.timestamp = erroBuilder.timestamp;
        this.type = erroBuilder.type;
        this.title = erroBuilder.title;
        this.detail = erroBuilder.detail;
        this.userMessage = erroBuilder.userMessage;
        this.objects = erroBuilder.objects;
    }

    public static ErroBuilder builder() {
        return new ErroBuilder();
    }

    public static class ErroBuilder {

        private Integer status;
        private OffsetDateTime timestamp;
        private String type;
        private String title;
        private String detail;
        private String userMessage;
        private List<Object> objects;

        ErroBuilder(){}

        public ErroBuilder withStatus(Integer status) {
            this.status = status;
            return this;
        }

        public ErroBuilder withTimestamp(OffsetDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public ErroBuilder withType(String type) {
            this.type = type;
            return this;
        }

        public ErroBuilder withTitle(String title) {
            this.title = title;
            return this;
        }

        public ErroBuilder withDetail(String detail) {
            this.detail = detail;
            return this;
        }

        public ErroBuilder withUserMessage(String userMessage) {
            this.userMessage = userMessage;
            return this;
        }

        public ErroBuilder withObjects(List<Object> objects) {
            this.objects = objects;
            return this;
        }

        public Erro build(){
            return new Erro(this);
        }
    }

    public static class Object {

        private String name;
        private String userMessage;

        public Object(String name, String userMessage) {
            this.name = name;
            this.userMessage = userMessage;
        }

        private Object(ObjectBuilder objectBuilder) {
            this.name = objectBuilder.name;
            this.userMessage = objectBuilder.userMessage;
        }

        public static ObjectBuilder builder() {
            return new ObjectBuilder();
        }

        public static class ObjectBuilder {
            private String name;
            private String userMessage;

            ObjectBuilder(){}

            public ObjectBuilder withName(String name) {
                this.name = name;
                return this;
            }

            public ObjectBuilder withUserMessage(String userMessage) {
                this.userMessage = userMessage;
                return this;
            }
            public Object build(){
                return new Object(this);
            }
        }
    }
}
