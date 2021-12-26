package br.com.professores.enums;

public enum Assunto {

    JAVA("Java"),
    C_SHARP("C#"),
    C("C"),
    PYTHON("Python"),
    JAVASCRIPT("Javascript"),
    SQL("SQL"),
    HTML("HTML"),
    CSS("CSS"),
    DOCKER("Docker"),
    AWS("AWS"),
    KUBERNETES("Kubernetes"),
    JSF("JSF"),
    SPRING_BOOT("Spring Boot");

    private String assunto;

    Assunto(String assunto) {
        this.assunto = assunto;
    }

    public String getAssunto() {
        return assunto;
    }


}
