package br.com.professores;

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


    public void definirSemanas() {

        for(int dia = 0, count = 0; dia < 365; dia++) {

            LocalDate data = dataInicio.plusDays(dia);
            int diaSemana = data.getDayOfWeek().getValue();
            if((diaSemana == 1 && count < 52) || (dia == 0 && diaSemana <= 5)) {
                String dia01 = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String dia05 = data.plusDays(5 - diaSemana)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                semanas.add("'"+nome+"' na semana do dia "+ dia01 + " até " +
                                dia05 + " terá '"+assuntos.get(count)+"'");
                datas.add(data);
                count++;
            }
        }
    }

    public void addProfessor(Docente d) {
        if(professores.size() < 2) {
            if (d.getTurma() != null && d.getTurma().equals(this)) {
                System.out.println("A turma '"+identificacao+
                        "' já contém o professor '"+d.getIdentificacao()+"'");
                return;
            }
            professores.add(d);
            d.setTurma(this);
        }
        else System.out.println("Operação inválida!\nA turma "+
                identificacao+ " já possui 02 professores!");
    }

    public List<Docente> getProfessores() {
        return professores;
    }

    public void setProfessores(List<Docente> professores) {
        this.professores = professores;
    }

    public List<LocalDate> getDatas() {
        return datas;
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

    public int getQuantidadeAlunos() {
        return quantidadeAlunos;
    }

    public void setQuantidadeAlunos(int quantidadeAlunos) {
        this.quantidadeAlunos = quantidadeAlunos;
    }

    public List<String> getAssuntos() {
        return assuntos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    @Override
    public String toString() {
        String texto = "\nIdentificação da turma: "+identificacao +
                        "\nQuantidade de alunos: "+quantidadeAlunos +
                        "\nData de início: "+ dataInicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                        (professores.isEmpty() ? "" : "\nDocentes: \n\t" +
                                "- Nome: "+ professores.get(0).getNome() + "\n\t- "+
                                "Identificação: "+ professores.get(0).getIdentificacao()) +
                        (professores.size() == 1 ? "" : "\n\n\t" +
                                "- Nome: "+ professores.get(1).getNome() + "\n\t- "+
                                "Identificação: "+ professores.get(1).getIdentificacao()) +
                        "\nSemanas e assuntos: ";

        for(String semana : semanas) {
            texto += "\n\t- "+ semana;
        }
        return texto;
    }

}
