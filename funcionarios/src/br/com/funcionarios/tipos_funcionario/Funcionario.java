package br.com.funcionarios.tipos_funcionario;

import br.com.funcionarios.interfaces.Calculadora;

public abstract class Funcionario implements Calculadora {

    private String nome;
    private String matricula;
    private double salario;
    private double gratificacao;
    private boolean admitido;

    public Funcionario(String nome, String matricula, double salario) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
        admitido = true;
    }

    @Override
    public double calculaSalario() {
        return salario - getImposto() + getGratificacao();
    }

    private double getImposto() {
        return salario * 0.1;
    }

    private double getGratificacao() {
        return salario * gratificacao;
    }

    protected void setGratificacao(double gratificacao) {
        this.gratificacao = gratificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    public boolean isAdmitido() {
        return admitido;
    }

    public void setAdmitido(boolean admitido) {
        this.admitido = admitido;
    }

    @Override
    public String toString() {
        return "\nCargo: " + exibirCargo() +
                "\nNome: " + nome +
                "\nMatrícula: " + matricula +
                "\nSalário bruto: " + salario +
                "\nSalário líquido: " + calculaSalario() +
                "\nEstá trabalhando: " + (admitido ? "sim" : "não");
    }

    private String exibirCargo() {
        String cargo = getClass().getSimpleName();
        for(int i = 0; i < cargo.length(); i++) {
            if(i > 0 && Character.isUpperCase(cargo.charAt(i))) {
                return cargo.substring(0,i) + " " + cargo.substring(i);
            }
        }
        return cargo;
    }

}
