package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);


    public static String menu(Map<Turma, List<Docente>> map) {

        System.out.println("\nEscolha uma opção:\n1 - Cadastrar turma" +
                "\n2 - Cadastrar docente\n3 - Cadastrar docente" +
                "\n4 - Atualizar informações de um funcionário\n5 - Listar todos os funcionários" +
                "\n6 - Listar somente os funcionários trabalhando" +
                "\n7 - Listar somente os funcionários demitidos\n9 - Encerrar sessão");

        String opcao = scanner.nextLine();
        switch (opcao.hashCode()) {
            case 49:
                opcao = GeradorTipo.cadastrarTurma(map);
                break;
            case 50:
                opcao = GeradorTipo.cadastrarDocente(map);
                break;
//            case 51:
//                opcao = Atualizacao.demitir(funcionarios);
//                break;
//            case 52:
//                opcao = Atualizacao.atualizarFuncionario(
//                        funcionarios,null,null,null);
//                break;
//            case 53:
//                opcao = Exibicao.listar(funcionarios, Empregado.TODOS);
//                break;
//            case 54:
//                opcao = Exibicao.listar(funcionarios, Empregado.TRABALHANDO);
//                break;
//            case 55:
//                opcao = Exibicao.listar(funcionarios, Empregado.DEMITIDO);
//                break;
//            case 57:
//                System.out.println("Volte sempre! Até a proxima.");
//                break;
            default:
                System.out.println("Opção inválida!");
        }

        return opcao.equals("9") ? "" : menu(map);
    }
}
