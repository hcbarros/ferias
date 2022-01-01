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
    private List<Docente> professores;
    private List<String> semanas;
    private List<LocalDate> datas;

    public Turma(String identificacao, String nome,
                 int quantidadeAlunos, LocalDate dataInicio) {

        this.identificacao = identificacao;
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
        this.dataInicio = dataInicio;
        professores = new ArrayList<>();
        semanas = new ArrayList<>();
        datas = new ArrayList<>();
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

    public List<Docente> getProfessores() {
        return professores;
    }

    public List<LocalDate> getDatas() {
        return datas;
    }

    public void addSemana(String semana) {
        semanas.add(semana);
    }

    public void addData(LocalDate data) {
        datas.add(data);
    }

    public boolean addProfessor(Docente d) {
        return professores.add(d);
    }


    @Override
    public String toString() {
        String texto = "\nIdentificação da turma: "+identificacao +
                        "\nNome da turma: "+nome +
                        "\nQuantidade de alunos: "+quantidadeAlunos +
                        "\nData de início: "+ dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        (professores.isEmpty() ? "" : "\nDocentes: \n\t" +
                                "- Nome: "+ professores.get(0).getNome() + "\n\t- "+
                                "Identificação: "+ professores.get(0).getIdentificacao()) +
                        (professores.size() < 2 ? "" : "\n\n\t" +
                                "- Nome: "+ professores.get(1).getNome() + "\n\t- "+
                                "Identificação: "+ professores.get(1).getIdentificacao()) +
                        "\n\nSemanas e assuntos: ";

        for(String semana : semanas) {
            texto += "\n\t- "+ semana;
        }
        return texto;
    }

}
