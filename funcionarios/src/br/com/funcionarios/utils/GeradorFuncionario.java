package br.com.funcionarios.utils;

import br.com.funcionarios.tipos_funcionario.*;

import java.util.List;
import java.util.Scanner;

public class GeradorFuncionario {

    private static Scanner scanner = new Scanner(System.in);


    public static String opcaoAddFUncionario(List<Funcionario> funcionarios) {

        System.out.println("\nEscolha uma opção:\n1 - Adicionar Colaborador" +
                "\n2 - Adicionar Líder Técnico\n3 - Adicionar Gerente de Departamento" +
                "\n4 - Adicionar Gerente Geral\n5 - Voltar ao menu principal\n9 - Encerrar a sessão");

        String opcao = scanner.nextLine();
        switch (opcao.hashCode()) {

            case 49:
                opcao = addFuncionario(funcionarios, Colaborador.class,
                        null,null,null);
                break;
            case 50:
                opcao = addFuncionario(funcionarios, LiderTecnico.class,
                        null,null,null);
                break;
            case 51:
                opcao = addFuncionario(funcionarios, GerenteDepartamento.class,
                        null,null,null);
                break;
            case 52:
                opcao = addFuncionario(funcionarios, GerenteGeral.class,
                        null,null,null);
                break;
            case 53: return "";
            case 57:
                System.out.println("Volte sempre! Até a proxima.");
                return "9";
            default:
                System.out.println("Opção inválida!");
                return opcaoAddFUncionario(funcionarios);
        }

        return "";
    }


    private static String addFuncionario(List<Funcionario> funcionarios, Class<?> classe,
                                     String nome, String matricula, String salario) {

        if(nome == null) {
            System.out.print("Informe o nome do funcionário: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) nome = null;

            return addFuncionario(funcionarios, classe, nome, matricula, salario);
        }
        else if(matricula == null) {
            System.out.print("Informe a matrícula do funcionário: ");
            matricula = scanner.nextLine();
            if(!matricula.matches("\\d{1,10}")) {
                System.out.println("A matrícula deve ser um número inteiro!");
                matricula = null;
            }
            else {
                for(Funcionario f: funcionarios) {
                    if(f.getMatricula().equals(matricula)) {
                        System.out.println("\nJá existe um funcionário com essa matrícula!");
                        matricula = null;
                    }
                }
            }
            return addFuncionario(funcionarios, classe, nome, matricula, salario);
        }
        else if(salario == null){
            System.out.print("Informe o salário do funcionário: ");
            salario = scanner.nextLine();
            if(!salario.matches("\\d{1,10}(\\.\\d{1,2})?$")) {
                System.out.println("\nO salário deve ser um número com no máximo 2 casas decimais!" +
                        "\nPor exemplo: 5555555555.22\nOutro exemplo: 10000");
                return addFuncionario(funcionarios, classe, nome, matricula, null);
            }
            Funcionario f;
            if(classe.equals(Colaborador.class)) {
                f = new Colaborador(nome, matricula, Double.parseDouble(salario));
            }
            else if(classe.equals(LiderTecnico.class)) {
                f = new LiderTecnico(nome, matricula, Double.parseDouble(salario));
            }
            else if(classe.equals(GerenteDepartamento.class)) {
                f = new GerenteDepartamento(nome, matricula, Double.parseDouble(salario));
            }
            else {
                f = new GerenteGeral(nome, matricula, Double.parseDouble(salario));
            }
            funcionarios.add(f);
        }
        System.out.println("\nFuncionário adicionado com sucesso!");
        return "";
    }

}
