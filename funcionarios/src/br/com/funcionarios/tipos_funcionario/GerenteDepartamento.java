package br.com.funcionarios.tipos_funcionario;

public class GerenteDepartamento extends Funcionario {

    public GerenteDepartamento(String nome, String matricula, double salario) {
        super(nome, matricula, salario);
        setGratificacao(salario * 0.2);
    }
}
