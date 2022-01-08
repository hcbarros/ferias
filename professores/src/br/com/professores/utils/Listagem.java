package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Semana;
import br.com.professores.tipos.Turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Listagem {

    private static Scanner scanner = new Scanner(System.in);


    public static String listarTurmas(List<Turma> turmas) {
        turmas.forEach(System.out::println);
        return "";
    }

    public static String listarDocentes(List<Docente> docentes) {
        for(Docente d: docentes) {
            List<Turma> list = new ArrayList<>();
            String turmas = "";
            for(Semana s: d.getSemanas()) {
                if(!list.contains(s.getTurma())) {
                    list.add(s.getTurma());
                    turmas += s.getTurma().getNome() +", ";
                }
            }
            System.out.println("Nome: "+d.getNome());
            System.out.println("Identificação: "+d.getIdentificacao());
            System.out.println(list.isEmpty() ? "Não está alocado em nenhuma turma!" :
                                "Turmas em que ensina: "+ turmas);
            System.out.println(d.getSemanas().isEmpty() ? "" :
                    "Total de semanas alocado: "+ d.getSemanas().size() + "\n");
        };
        return "";
    }

    public static String listarDocenteSemanasAula(List<Docente> docentes) {

        System.out.print("Informe o identificador do docente ou 'sair' para voltar ao menu anterior: ");
        String id = scanner.nextLine();
        if(id.equalsIgnoreCase("sair")) return "";
        boolean find = false;
        for(Docente d: docentes) {
            if(d.getIdentificacao().equals(id)) {
                System.out.println(d);
                return "";
            }
        }
        System.out.println("\nNão há nenhum docente com o identificador informado!");
        return listarDocenteSemanasAula(docentes);
    }

}
