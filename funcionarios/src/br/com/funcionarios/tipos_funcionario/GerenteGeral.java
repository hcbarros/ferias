package br.com.funcionarios.tipos_funcionario;

public class GerenteGeral extends Funcionario {

    public GerenteGeral(String nome, String matricula, double salario) {
        super(nome, matricula, salario);
        setGratificacao(0.3);
    }
}
