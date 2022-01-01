package br.com.funcionarios.utils;

import br.com.funcionarios.enums.Empregado;
import br.com.funcionarios.tipos_funcionario.Funcionario;

import java.util.List;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);


    public static String menu(List<Funcionario> funcionarios) {

        System.out.println("\nEscolha uma opção:\n1 - Adicionar funcionário" +
                "\n2 - Ver detalhes de um funcionário\n3 - Demitir funcionário" +
                "\n4 - Atualizar informações de um funcionário\n5 - Listar todos os funcionários" +
                "\n6 - Listar somente os funcionários trabalhando" +
                "\n7 - Listar somente os funcionários demitidos\n8 - Encerrar sessão");

        String opcao = scanner.nextLine();
        switch (opcao.hashCode()) {
            case 49:
                opcao = GeradorFuncionario.opcaoAddFUncionario(funcionarios);
                break;
            case 50:
                opcao = Exibicao.detalhesFuncionario(funcionarios);
                break;
            case 51:
                opcao = Atualizacao.demitir(funcionarios);
                break;
            case 52:
                opcao = Atualizacao.atualizarFuncionario(
                        funcionarios,null,null,null);
                break;
            case 53:
                opcao = Exibicao.listar(funcionarios, Empregado.TODOS);
                break;
            case 54:
                opcao = Exibicao.listar(funcionarios, Empregado.TRABALHANDO);
                break;
            case 55:
                opcao = Exibicao.listar(funcionarios, Empregado.DEMITIDO);
                break;
            case 56:
                System.out.println("Volte sempre! Até a proxima.");
                break;
            default:
                System.out.println("Opção inválida!");
        }

        return opcao.equals("8") ? "" : menu(funcionarios);
    }

}
