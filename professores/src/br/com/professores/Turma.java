package br.com.professores;

import br.com.professores.enums.Assunto;

import java.time.LocalDate;
import java.util.List;

public class Turma {

    private String identificacao;
    private String nome;
    private int quantidadeAlunos;
    private List<Assunto> assuntos;
    private LocalDate dataInicio;

    public Turma(String identificacao, String nome, int quantidadeAlunos,
                 List<Assunto> assuntos, LocalDate dataInicio) {

        this.identificacao = identificacao;
        this.nome = nome;
        this.quantidadeAlunos = quantidadeAlunos;
        this.assuntos = assuntos;
        this.dataInicio = dataInicio;
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

    public List<Assunto> getAssuntos() {
        return assuntos;
    }

    public void setAssuntos(List<Assunto> assuntos) {
        this.assuntos = assuntos;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

}
