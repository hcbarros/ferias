package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;

import java.util.List;
import java.util.Scanner;


public class MenuPrincipal {

    private static Scanner scanner = new Scanner(System.in);


    public static String menu(List<Turma> turmas, List<Docente> docentes) {

        System.out.println("\nEscolha uma opção:\n1 - Cadastrar turma" +
                "\n2 - Cadastrar docente\n3 - Definir docentes para uma determinada turma" +
                "\n4 - Listar turmas com seus respectivos assuntos, docentes e semanas" +
                "\n5 - Listar todos os docentes" +
                "\n6 - Listar um docente e suas semanas de aula já definidas\n7 - Sair");

        String opcao = scanner.nextLine();
        switch (opcao.hashCode()) {
            case 49:
                opcao = Cadastro.cadastrarTurma(turmas);
                break;
            case 50:
                opcao = Cadastro.cadastrarDocente(docentes);
                break;
            case 51:
                opcao = Cadastro.definirDocentes(turmas, docentes);
                break;
            case 52:
                opcao = Listagem.listarTurmas(turmas);
                break;
            case 53:
                opcao = Listagem.listarDocentes(docentes);
                break;
            case 54:
                opcao = Listagem.listarDocenteSemanasAula(docentes);
                break;
            case 55:
                return "";
            default:
                System.out.println("Opção inválida!");
        }
        return menu(turmas, docentes);
    }
}
