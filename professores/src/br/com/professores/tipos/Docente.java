package br.com.professores.tipos;


public class Docente {

    private String identificacao;
    private String nome;
    private Turma turma;

    public Docente(String identificacao, String nome) {
        this.identificacao = identificacao;
        this.nome = nome;
    }


    public String getIdentificacao() {
        return identificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public Turma getTurma() {
        return turma;
    }

    @Override
    public String toString() {
        return "\nIdentificação: "+identificacao +
                "\nNome: "+nome +
                (turma == null ? "\nSem turma definida!" :
                  "\nNome da turma: " + turma.getNome() +
                  "\nIdentificação da turma: "+ turma.getIdentificacao());
    }

}
