package br.com.professores;

public class Docente {

    private String identificacao;
    private String nome;
    private Turma turma;

    public Docente(String identificacao, String nome, Turma turma) {
        this.identificacao = identificacao;
        this.nome = nome;
        this.turma = turma;
    }


    public String getIdentificacao() {
        return identificacao;
    }

    public void setIdentificacao(String identificacao) {
        this.identificacao = identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }
}
