package br.com.zup.casadocodigo.compartilhado.exceptionhandler;

public enum TipoErro {

    DADOS_INVALIDOS("/dados-invalidos", "Dados inválidos"),
    ACESSO_NEGADO("/acesso-negado", "Acesso negado"),
    ERRO_DE_SISTEMA("/erro-de-sistema", "Erro de sistema"),
    PARAMETRO_INVALIDO("/parametro-invalido", "Parâmetro inválido"),
    MENSAGEM_INCOMPREENSIVEL("/mensagem-incompreensivel", "Mensagem incompreensível"),
    RECURSO_NAO_ENCONTRADO("/recurso-nao-encontrado", "Recurso não encontrado"),
    ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
    ERRO_NEGOCIO("/erro-negocio", "Violação de regra de negócio");

    private String title;
    private String uri;

    TipoErro(String path, String title) {
        this.uri = "http://www.casadocodigo.com.br" + path;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getUri() {
        return uri;
    }
}
