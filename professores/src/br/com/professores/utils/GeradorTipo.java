package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GeradorTipo {

    private static Scanner scanner = new Scanner(System.in);


    public static String cadastrarTurma(Map<Turma, List<Docente>> map) {
        return cadastrarTurma(map, null, null, null, null);
    }

    private static String cadastrarTurma(Map<Turma, List<Docente>> map,
                                         String id, String nome, String quant, String inicio) {

        if(id == null) {
            System.out.print("Informe a identificação da turma: ");
            id = scanner.nextLine();
            for(Turma t: map.keySet()) {
                if(t != null && t.getIdentificacao().equals(id)) {
                    System.out.println("Já existe uma turma com essa mesma identificação!");
                    id = null;
                }
            }
            if(id != null && (id.isBlank() || id.isEmpty())) id = null;
            return cadastrarTurma(map, id, null, null, null);
        }
        else if(nome == null) {
            System.out.print("Informe o nome da turma: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) nome = null;
            return cadastrarTurma(map, id, nome, null, null);
        }
        else if(quant == null) {
            System.out.print("Informe a quantidade de alunos: ");
            quant = scanner.nextLine();
            if(!quant.matches("\\d{1,10}")) {
                System.out.println("\nA quantidade de alunos deve ser um número inteiro positivo!");
                quant = null;
            }
            return cadastrarTurma(map, id, nome, quant, null);
        }
        else if(inicio == null) {
            System.out.print("Informe a data de início da turma (ex: 10/10/2022): ");
            inicio = scanner.nextLine();
            try {
                LocalDate ld = LocalDate.parse(inicio, DateTimeFormatter
                        .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

                map.put(new Turma(id,nome,Integer.parseInt(quant), ld), new ArrayList<>());
                System.out.println("\nTurma cadastrada com sucesso!");
            }
            catch (DateTimeParseException exc) {
                System.out.println("\nDigite uma data válida, seguindo o formato -> (dd/MM/AAAA)");
                return cadastrarTurma(map, id, nome, quant, null);
            }
        }
        return "";
    }


    public static String cadastrarDocente(Map<Turma, List<Docente>> map) {
        return cadastrarDocente(map, null, null);
    }

    private static String cadastrarDocente(Map<Turma, List<Docente>> map, String id, String nome) {

        if(id == null) {
            System.out.print("Informe a identificação do docente: ");
            id = scanner.nextLine();

            for(Map.Entry<Turma, List<Docente>> mapa : map.entrySet()) {
                for(Docente d: mapa.getValue()) {
                    if(d.getIdentificacao().equals(id)) {
                        System.out.println("Já existe um docente com essa mesma identificação!");
                        id = null;
                    }
                }
            }
            if(id != null && (id.isBlank() || id.isEmpty())) id = null;
            return cadastrarDocente(map, id, null);
        }
        else if(nome == null) {
            System.out.print("Informe o nome do docente: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) {
                return cadastrarDocente(map, id, null);
            }
            List<Docente> list = map.get(null);
            list.add(new Docente(id,nome));
            map.put(null, list);
            System.out.println("\nDocente cadastrado com sucesso!");
        }
        return "";
    }


}
