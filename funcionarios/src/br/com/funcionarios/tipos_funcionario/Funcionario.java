package br.com.funcionarios.tipos_funcionario;

public abstract class Funcionario {

    private String nome;
    private String matricula;
    private double salario;
    private double imposto;
    private double gratificacao;
    private boolean admitido;

    public Funcionario(String nome, String matricula, double salario) {
        this.nome = nome;
        this.matricula = matricula;
        this.salario = salario;
        admitido = true;
        imposto = salario * 0.1;
    }

    public double calculaSalario() {
        return salario - imposto + gratificacao;
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

    public void setGratificacao(double gratificacao) {
        this.gratificacao = gratificacao;
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
                cargo = cargo.substring(0,i) + " " + cargo.substring(i);
                break;
            }
        }
        return cargo;
    }

}