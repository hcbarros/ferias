package br.com.professores;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public void semanasDocente() {

        if(turma != null) {
            List<LocalDate> datas = turma.getDatas();
            for(int i = 0; i < datas.size(); i++) {
                int diaSemana = datas.get(i).getDayOfWeek().getValue();
                int plus = 5;
                if(diaSemana <= 2 && turma.getProfessores().size() > 1) {
                    plus = 2;
                }
                String dia01 = datas.get(i)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                LocalDate plusDay = datas.get(i).plusDays(plus - diaSemana);

                if(turma.getProfessores().get(0).equals(this)) {
                    System.out.println(nome + " (identificação: "+ identificacao +
                                "), lecionou do dia "+ dia01 + " até dia "+ plusDay
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
                else {
                    LocalDate primeiro = plus == 2 ? plusDay.plusDays(1) : datas.get(i);
                    System.out.println(nome + " (identificação: "+ identificacao +"), lecionou "+
                            "do dia "+ primeiro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                            " até dia "+ plusDay.plusDays(plus == 2 ? 3 : 0)
                            .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                }
            }
        }
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
