package br.com.professores.utils;

import br.com.professores.tipos.Docente;
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
                String dia01 = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String dia05 = data.plusDays(5 - diaSemana)
                        .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                turma.addSemana("'"+turma.getNome()+"' na semana do dia "+ dia01 + " até " +
                        dia05 + " terá '"+turma.getAssuntos().get(count)+"'");
                turma.addData(data);
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
        return definirDocentes(turmas, docentes,null,null);
    }

    private static String definirDocentes(List<Turma> turmas, List<Docente> docentes,
                                          Turma turma, String idProf) {

        if(turma == null) {
            System.out.print("Informe o identificador da turma ou 'sair' para voltar ao menu anterior: ");
            String idTurma = scanner.nextLine();
            if(idTurma.equalsIgnoreCase("sair")) return "";
            for(Turma t: turmas) {
                if(t.getIdentificacao().equals(idTurma)) {
                    if(t.getProfessores().size() == 2) {
                        System.out.println("\nEssa turma já possui o máximo de 02 professores alocados!");
                        t = null;
                    }
                    return definirDocentes(turmas, docentes, t,null);
                }
            }
            System.out.println("\nNão há nenhuma turma cadastrada com o identificador informado!");
            return definirDocentes(turmas, docentes,null,null);
        }
        else if(idProf == null) {
            System.out.print("Informe o identificador do docente ou 'sair' para voltar ao menu anterior: ");
            idProf = scanner.nextLine();
            if(idProf.equalsIgnoreCase("sair")) return "";
            Docente docente = null;
            for(Docente d: docentes) {
                if(d.getIdentificacao().equals(idProf)) {
                    if(d.getTurma() != null) {
                        System.out.println("\nO docente '"+ d.getIdentificacao()+
                                "' já está alocado em uma turma");
                        return definirDocentes(turmas, docentes, turma,null);
                    }
                    docente = d;
                    break;
                }
            }
            if(docente != null) {
                int indexTurma = turmas.indexOf(turma);
                int indexDocente = docentes.indexOf(docente);
                boolean bool = addProfessor(turma, docente);
                if(bool) {
                    docentes.set(indexDocente, docente);
                    turmas.set(indexTurma, turma);
                    System.out.println("\nO docente '"+docente.getIdentificacao()+
                            "' foi definido para a turma '"+ turma.getIdentificacao()+"'");
                }
                return "";
            }

        }
        System.out.println("\nNão há nenhum docente cadastrado com o identificador informado!");
        return definirDocentes(turmas, docentes, turma,null);
    }


    public static boolean addProfessor(Turma t, Docente d) {
        if(t.getProfessores().size() < 2) {
            if (d.getTurma() != null && d.getTurma().equals(t)) {
                System.out.println("\nA turma '"+t.getIdentificacao()+
                        "' já contém o professor '"+d.getIdentificacao()+"'");
                return false;
            }
            d.setTurma(t);
            return t.addProfessor(d);
        }
        else System.out.println("\nOperação inválida!\nA turma "+
                t.getIdentificacao()+ " já possui 02 professores!");
        return false;
    }

}
