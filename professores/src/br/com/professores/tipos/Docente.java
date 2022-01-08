package br.com.professores.tipos;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Docente {

    private String identificacao;
    private String nome;
    private List<Semana> semanas;
    List<LocalDate> diasSemana;

    public Docente(String identificacao, String nome) {
        this.identificacao = identificacao;
        this.nome = nome;
        semanas = new ArrayList<>();
        diasSemana = new ArrayList<>();
    }


    public String getIdentificacao() {
        return identificacao;
    }

    public String getNome() {
        return nome;
    }

    public List<Semana> getSemanas() {
        return semanas;
    }

    public boolean addSemana(Semana semana, List<LocalDate> dias) {
        for(Semana s: semanas) {
            LocalDate dia = s.getInicio();
            for(LocalDate d: dias) {
                 if(dia.isEqual(d)) {
                     System.out.println("\nEsse docente já está alocado nesse período!");
                     return false;
                 }
                 dia = dia.plusDays(1);
            }
        }
        return semanas.add(semana);
    }


    @Override
    public String toString() {

        String listaSemanas = "\nSemanas de aula: ";

        for(Semana s: semanas) {
            String dias = "";
            List<LocalDate> list = s.getDocente1().equals(this) ?
                    s.getDiasDocente1() : s.getDiasDocente2();
            for(LocalDate dia: list) {
                dias += dia.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))+", ";
            }
            listaSemanas += "\n\t- Turma: " + s.getTurma().getNome() +
                            "\n\t- Dias da semana: "+ dias + "\n\t- Assumto: " + s.getAssunto() +
                            "\n\t--------------------------------------------";
        }
        return "\nIdentificação: "+identificacao +
                "\nNome: "+nome +
                (semanas.isEmpty() ? "\nNão está alocado em nenhuma turma!" : listaSemanas);
    }

}
