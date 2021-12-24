package br.com.funcionarios.tipos_funcionario;

public class LiderTecnico extends Funcionario {

    public LiderTecnico(String nome, String matricula, double salario) {
        super(nome, matricula, salario);
        setGratificacao(salario * 0.1);
    }
}
