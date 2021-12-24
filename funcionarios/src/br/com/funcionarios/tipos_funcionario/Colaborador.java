package br.com.funcionarios.tipos_funcionario;

public class Colaborador extends Funcionario {

    public Colaborador(String nome, String matricula, double salario) {
        super(nome, matricula, salario);
        setGratificacao(salario * 0.05);
    }
}
