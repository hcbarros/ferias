package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;


public class Listagem {

    private static Scanner scanner = new Scanner(System.in);


    public static String listarTurmas(List<Turma> turmas) {
        if(turmas.isEmpty()) System.out.println("\nNão há nenhuma turma cadastrada!");
        turmas.forEach(System.out::println);
        return "";
    }

    public static String listarDocentes(List<Docente> docentes) {
        if(docentes.isEmpty()) System.out.println("\nNão há nenhum docente cadastrado!");
        docentes.forEach(System.out::println);
        return "";
    }

    public static String listarDocenteSemanasAula(List<Docente> docentes) {

        System.out.print("Informe o identificador do docente ou 'sair' para voltar ao menu anterior: ");
        String id = scanner.nextLine();
        if(id.equalsIgnoreCase("sair")) return "";
        for(Docente d: docentes) {
            if(d.getIdentificacao().equals(id)) {

                if(d.getTurma() != null) {
                    List<LocalDate> datas = d.getTurma().getDatas();
                    for(int i = 0; i < datas.size(); i++) {
                        String tempoVerbal = datas.get(i).isBefore(LocalDate.now()) ?
                                "lecionou" : "lecionará";

                        int diaSemana = datas.get(i).getDayOfWeek().getValue();
                        int plus = 5;
                        if(diaSemana <= 2 && d.getTurma().getProfessores().size() > 1) {
                            plus = 2;
                        }
                        String dia01 = datas.get(i)
                                .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                        LocalDate plusDay = datas.get(i).plusDays(plus - diaSemana);

                        if(d.getTurma().getProfessores().get(0).equals(d)) {
                            if((i > 0) || (i == 0 && plus == 2) || (d.getTurma().getProfessores().size() == 1)) {
                                System.out.println(d.getNome() + " (identificação: " + d.getIdentificacao() +
                                        "), " + tempoVerbal + " do dia " + dia01 + " até dia " + plusDay
                                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                            }
                        }
                        else {
                            LocalDate primeiro = plus == 2 ? plusDay.plusDays(1) : datas.get(i);
                            System.out.println(d.getNome() + " (identificação: "+ d.getIdentificacao() +"), " +tempoVerbal +
                                    " do dia "+ primeiro.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) +
                                    " até dia "+ plusDay.plusDays(plus == 2 ? 3 : 0)
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                        }
                    }
                }
                else {
                    System.out.println("\nO docente informado não está alocado em nenhuma turma!");
                    return listarDocenteSemanasAula(docentes);
                }
                return "";
            }
        }
        System.out.println("\nNão há nenhum docente com o identificador informado!");
        return listarDocenteSemanasAula(docentes);
    }

}
