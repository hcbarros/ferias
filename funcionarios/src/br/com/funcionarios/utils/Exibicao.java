package br.com.funcionarios.utils;

import br.com.funcionarios.enums.Empregado;
import br.com.funcionarios.tipos_funcionario.Funcionario;

import java.util.List;
import java.util.Scanner;


public class Exibicao {

    private static Scanner scanner = new Scanner(System.in);


    public static String detalhesFuncionario(List<Funcionario> funcionarios) {

        String resp = Atualizacao.localizar(funcionarios);
        if(resp == null) {
            return detalhesFuncionario(funcionarios);
        }
        int index = Integer.parseInt(resp);
        if(index < 0) return "";

        System.out.println(funcionarios.get(index));
        return "";
    }

    public static String listar(List<Funcionario> funcionarios, Empregado e) {

        boolean find = false;
        for(Funcionario f: funcionarios) {
            boolean imprimir = false;
            if(e.equals(Empregado.TRABALHANDO) && f.isAdmitido()) {
                imprimir = true;
            }
            else if(e.equals(Empregado.DEMITIDO) && !f.isAdmitido()) {
                imprimir = true;
            }
            else if(e.equals(Empregado.TODOS)) {
                imprimir = true;
            }
            if(imprimir) {
                System.out.println("\nNome: " + f.getNome());
                System.out.println("Matrícula: " + f.getMatricula());
                find = true;
            }
        }
        if(!find) {
            System.out.println("Não há registros que atendam a busca!");
        }
        return "";
    }




}
