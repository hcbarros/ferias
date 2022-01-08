package br.com.professores.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


public class Turma {

    private String identificacao;
    private String nome;
    private int quantidadeAlunos;
    private LocalDate dataInicio;
    private List<String> assuntos;
    private List<Semana> semanas;

    public Turma(String identificacao, String nome,
                 int quantidadeAlunos, LocalDate dataInicio) {

        this.identificacao = identificacao;
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
        this.dataInicio = dataInicio;
        semanas = new ArrayList<>();
        assuntos = List.of("Java","Java", "Java", "Java", "C#","C#","C#","C#","C","C","C","C",
                "Python","Python","Python","Python","Javascript","Javascript",
                "Javascript","Javascript","SQL","SQL","SQL","SQL","HTML","HTML","HTML","HTML",
                "CSS","CSS","CSS","CSS","DOCKER","DOCKER","DOCKER","DOCKER",
                "AWS","AWS","AWS","AWS","Kubernetes","Kubernetes","Kubernetes","Kubernetes",
                "JSF","JSF","JSF","JSF","Spring Boot","Spring Boot","Spring Boot","Spring Boot");
    }


    public String getIdentificacao() {
        return identificacao;
    }

    public String getNome() {
        return nome;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public List<Semana> getSemanas() {
        return semanas;
    }

    public void addSemana(Semana semana) {
        semanas.add(semana);
        semana.setTurma(this);
    }

    public Semana getSemana(int index) {
        return semanas.get(index);
    }

    public boolean definirDocente(Semana semana, Docente docente) {

        int index = semanas.indexOf(semana);
        boolean definido = false;
        if(semana.getDocente1() != null && semana.getDocente2() != null) {
            System.out.println("\nEssa semana já possui 02 professores alocados!");
            return false;
        }
        else {
            if(semana.getDocente1() == null) {
                definido = semana.setDocente1(docente);
            }
            else definido = semana.setDocente2(docente);
            if(definido) semanas.set(index, semana);
        }
        return definido;
    }


    @Override
    public String toString() {
        String texto = "\nIdentificação da turma: "+identificacao +
                        "\nNome da turma: "+nome +
                        "\nQuantidade de alunos: "+quantidadeAlunos +
                        "\nData de início: "+ dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        "\nAssuntos, docentes e semanas: ";

        for(Semana semana : semanas) {
            texto += semana;
        }
        return texto;
    }

}
