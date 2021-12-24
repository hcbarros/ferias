package br.com.funcionarios.utils;

import br.com.funcionarios.tipos_funcionario.Funcionario;

import java.util.List;
import java.util.Scanner;


public class Exibicao {

    private static Scanner scanner = new Scanner(System.in);


    public static String detalhesFuncionario(List<Funcionario> funcionarios) {

        System.out.print("\nDigite a matrícula do funcionário" +
                " ou 'sair' para voltar ao menu anterior: ");
        String matricula = scanner.nextLine();

        if(matricula.equalsIgnoreCase("sair")) return "";
        boolean find = false;
        for(Funcionario f: funcionarios) {
            if(f.getMatricula().equals(matricula)) {
                System.out.println(f);
                find = true;
            }
        }
        if(!find) {
            System.out.println("\nNão há nenhum funcionário " +
                    "cadastrado com a matrícula informada!");
        }
        return detalhesFuncionario(funcionarios);
    }




}
