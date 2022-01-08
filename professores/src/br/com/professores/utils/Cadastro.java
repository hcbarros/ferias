package br.com.professores.utils;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Semana;
import br.com.professores.tipos.Turma;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

import java.util.List;
import java.util.Scanner;


public class Cadastro {

    private static Scanner scanner = new Scanner(System.in);


    public static String cadastrarTurma(List<Turma> turmas) {
        return cadastrarTurma(turmas, null, null, null, null);
    }

    private static String cadastrarTurma(List<Turma> turmas, String id,
                                         String nome, String quant, String inicio) {

        if(id == null) {
            System.out.print("Informe a identificação da turma: ");
            id = scanner.nextLine();
            for(Turma t: turmas) {
                if(t.getIdentificacao().equals(id)) {
                    System.out.println("Já existe uma turma com essa mesma identificação!");
                    id = null;
                }
            }
            if(id != null && (id.isBlank() || id.isEmpty())) id = null;
            return cadastrarTurma(turmas, id,null,null,null);
        }
        else if(nome == null) {
            System.out.print("Informe o nome da turma: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) nome = null;
            return cadastrarTurma(turmas, id, nome,null,null);
        }
        else if(quant == null) {
            System.out.print("Informe a quantidade de alunos: ");
            quant = scanner.nextLine();
            if(!quant.matches("\\d{1,10}")) {
                System.out.println("\nA quantidade de alunos deve ser um número inteiro positivo!");
                quant = null;
            }
            return cadastrarTurma(turmas, id, nome, quant,null);
        }
        else if(inicio == null) {
            System.out.print("Informe a data de início da turma (ex: 10/10/2022): ");
            inicio = scanner.nextLine();
            try {
                LocalDate ld = LocalDate.parse(inicio, DateTimeFormatter
                        .ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT));

                Turma t = new Turma(id,nome,Integer.parseInt(quant), ld);
                t = definirSemanas(t);
                turmas.add(t);
                System.out.println("\nTurma cadastrada com sucesso!");
            }
            catch (DateTimeParseException exc) {
                System.out.println("\nDigite uma data válida, seguindo o formato -> (dd/MM/AAAA)");
                return cadastrarTurma(turmas, id, nome, quant, null);
            }
        }
        return "";
    }

    public static Turma definirSemanas(Turma turma) {

        for(int dia = 0, count = 0; dia < 365; dia++) {

            LocalDate data = turma.getDataInicio().plusDays(dia);
            int diaSemana = data.getDayOfWeek().getValue();
            if((diaSemana == 1 && count < 52) || (dia == 0 && diaSemana <= 5)) {
                Semana semana = new Semana(data, data.plusDays(5 - diaSemana), turma.getAssuntos().get(count));
                turma.addSemana(semana);
                count++;
            }
        }
        return turma;
    }


    public static String cadastrarDocente(List<Docente> docentes) {
        return cadastrarDocente(docentes,null,null);
    }

    private static String cadastrarDocente(List<Docente> docentes, String id, String nome) {

        if(id == null) {
            System.out.print("Informe a identificação do docente: ");
            id = scanner.nextLine();
            for(Docente d: docentes) {
                if(d.getIdentificacao().equals(id)) {
                    System.out.println("\nJá existe um docente com essa mesma identificação!");
                    return cadastrarDocente(docentes,null,null);
                }
            }
            if(id != null && (id.isBlank() || id.isEmpty())) id = null;
            return cadastrarDocente(docentes, id,null);
        }
        else if(nome == null) {
            System.out.print("Informe o nome do docente: ");
            nome = scanner.nextLine();
            if(nome.isBlank() || nome.isEmpty()) {
                return cadastrarDocente(docentes, id,null);
            }
            docentes.add(new Docente(id,nome));
            System.out.println("\nDocente cadastrado com sucesso!");
        }
        return "";
    }


    public static String definirDocentes(List<Turma> turmas, List<Docente> docentes) {
        return definirDocentes(turmas, docentes,null,null,null,null);
    }

    private static String definirDocentes(List<Turma> turmas, List<Docente> docentes,
                                          Turma turma, Docente docente, String semana, Semana week) {

        if(turma == null) {
            System.out.print("Informe o identificador da turma ou 'sair' para voltar ao menu anterior: ");
            String idTurma = scanner.nextLine();
            if(idTurma.equalsIgnoreCase("sair")) return "";
            for(Turma t: turmas) {
                if(t.getIdentificacao().equals(idTurma)) {
                    return definirDocentes(turmas, docentes, t,null,null,null);
                }
            }
            System.out.println("\nNão há nenhuma turma cadastrada com o identificador informado!");
            return definirDocentes(turmas, docentes,null,null,null,null);
        }
        else if(docente == null) {
            System.out.print("Informe o identificador do docente ou 'sair' para voltar ao menu anterior: ");
            String idProf = scanner.nextLine();
            if (idProf.equalsIgnoreCase("sair")) return "";
            for (Docente d : docentes) {
                if (d.getIdentificacao().equals(idProf)) {
                    return definirDocentes(turmas, docentes, turma, d,null,null);
                }
            }
            System.out.println("\nNão há nenhum docente cadastrado com o identificador informado!");
            return definirDocentes(turmas, docentes, turma, null,null,null);
        }
        else if(semana == null) {
            System.out.print("Informe o número da semana de 1 a 52 ou 'sair' para voltar ao menu anterior: ");
            semana = scanner.nextLine();
            if (semana.equalsIgnoreCase("sair")) return "";
            if (!semana.matches("\\d{1,2}") || Integer.parseInt(semana) < 1 || Integer.parseInt(semana) > 52) {
                System.out.println("\nValor inválido!");
                return definirDocentes(turmas, docentes, turma, docente, null,null);
            }
            return definirDocentes(turmas, docentes, turma, docente, semana,null);
        }
        else if(week == null) {
            int num = Integer.parseInt(semana);
            week = turma.getSemana(num - 1);

            String inicio = Semana.getDataFormatada(week.getInicio());
            String fim = Semana.getDataFormatada(week.getFim());
            System.out.print("\nInício da semana: "+inicio + "\nFim da semana: "+fim +
                                "\nAssunto: "+week.getAssunto() +"\n\nConfirmar semana (s/n): ");

            String confirma = scanner.nextLine();
            if(!confirma.equalsIgnoreCase("s")) {
                if(!confirma.equalsIgnoreCase("n")) {
                    System.out.println("\nOpção inválida!");
                    return definirDocentes(turmas, docentes, turma, docente, semana,null);
                }
                return definirDocentes(turmas, docentes, turma, docente,null,null);
            }

            if(week.contemDocente(docente)) {
                System.out.println("\nO docente informado já está alocado nessa semana!");
                return definirDocentes(turmas, docentes, turma, docente,null,null);
            }
            int indexDocente = docentes.indexOf(docente);
            int indexTurma = turmas.indexOf(turma);
            boolean definido = turma.definirDocente(week, docente);
            if(!definido) {
                return definirDocentes(turmas, docentes, turma, docente,null,null);
            }
            docentes.set(indexDocente, docente);
            turmas.set(indexTurma, turma);
            System.out.println("\nO docente " + docente.getIdentificacao() +
                                " foi definido para a turma " + turma.getIdentificacao());
        }

        return "";
    }

}
