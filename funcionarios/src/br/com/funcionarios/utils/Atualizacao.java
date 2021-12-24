package br.com.funcionarios.utils;

import br.com.funcionarios.tipos_funcionario.Funcionario;

import java.util.List;
import java.util.Scanner;

public class Atualizacao {

    private static Scanner scanner = new Scanner(System.in);


    public static String demitir(List<Funcionario> funcionarios) {

        String index = localizar(funcionarios);

        if(index == null) return demitir(funcionarios);

        int i = Integer.parseInt(index);
        if(i < 0) return "";
        funcionarios.get(i).setAdmitido(false);
        System.out.println("O funcionário foi demitido!");
        return "";
    }


    public static String atualizarFuncionario(List<Funcionario> funcionarios,
                                              String index, String nome, String salario) {
        if(index == null) {
            index = localizar(funcionarios);
            if (index == null) {
                return atualizarFuncionario(funcionarios, null, null, null);
            }
        }
        int i = Integer.parseInt(index);
        if(i < 0) return "";

        if(nome == null) {
            System.out.print("Digite um nome para o funcionário: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) nome = null;
            return atualizarFuncionario(funcionarios, index, nome, null);
        }
        else if(salario == null) {
            System.out.print("Informe um salário para o funcionário: ");
            salario = scanner.nextLine();
            if(!salario.matches("\\d{1,10}(\\.\\d{1,2})?$")) {
                System.out.println("\nO salário deve ser um número com no máximo 2 casas decimais!" +
                        "\nPor exemplo: 5555555555.22\nOutro exemplo: 10000");
                return atualizarFuncionario(funcionarios, index, nome, null);
            }
        }
        else if(!funcionarios.get(i).isAdmitido()) {
            System.out.println("\nDeseja admitir o funcionário: \n1- Sim\n2 - Não");
            String admitir = scanner.nextLine();
            switch (admitir.hashCode()) {
                case 49:
                    funcionarios.get(i).setAdmitido(true);
                    break;
                case 50: break;
                default:
                    System.out.println("\nOpção inválida!");
                    return atualizarFuncionario(funcionarios, index, nome, salario);
            }
        }

        funcionarios.get(i).setNome(nome);
        funcionarios.get(i).setSalario(Double.parseDouble(salario));
        return "";
    }


    private static String localizar(List<Funcionario> funcionarios) {

        System.out.print("Digite a matrícula do funcionário" +
                " ou 'sair' para voltar ao menu anterior: ");
        String matricula = scanner.nextLine();
        if(matricula.equalsIgnoreCase("sair")) return "-1";

        for(Funcionario f: funcionarios) {
            if(f.getMatricula().equals(matricula)) {
                return String.valueOf(funcionarios.indexOf(f));
            }
        }
        System.out.println("\nNão há nenhum funcionário " +
                "cadastrado com a matrícula informada!");
        return null;
    }

}
