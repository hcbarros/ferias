package br.com.professores.tipos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Semana {

    private Docente docente1;
    private Docente docente2;
    private LocalDate inicio;
    private LocalDate fim;
    private String assunto;
    private Turma turma;
    private List<LocalDate> diasDocente1;
    private List<LocalDate> diasDocente2;

    public Semana(LocalDate inicio, LocalDate fim, String assunto) {
        this.inicio = inicio;
        this.fim = fim;
        this.assunto = assunto;
        diasDocente1 = new ArrayList<>();
        diasDocente2 = new ArrayList<>();
    }


    public Docente getDocente1() {
        return docente1;
    }

    public Docente getDocente2() {
        return docente2;
    }

    public boolean setDocente1(Docente docente1) {
        LocalDate dia = getInicio();
        List<LocalDate> list = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            if(!dia.isAfter(fim)) {
                list.add(dia);
                dia = dia.plusDays(1);
            }
        }
        boolean adicionado = docente1.addSemana(this, list);
        if(adicionado) {
            setDiasDocente1(list);
            this.docente1 = docente1;
        }
        return adicionado;
    }

    public boolean setDocente2(Docente docente2) {
        LocalDate dia = getInicio();
        List<LocalDate> list = new ArrayList<>();
        List<LocalDate> list1 = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            if(!dia.isAfter(fim)) {
                int num = dia.getDayOfWeek().getValue();
                if(i == 0 && num > 2) {
                    System.out.println("\nAs aulas dessa semana começaram após a terça-feira, "+
                                        "\nportanto ficará apenas com o professor de iddentificador: "+
                                            docente1.getIdentificacao());
                    return false;
                }
                if(num >= 3) list.add(dia);
                else list1.add(dia);
                dia = dia.plusDays(1);
            }
        }
        boolean adicionado = docente2.addSemana(this, list);
        if(adicionado) {
            setDiasDocente2(list);
            setDiasDocente1(list1);
            this.docente2 = docente2;
        }
        return adicionado;
    }

    public LocalDate getInicio() {
        return inicio;
    }

    public void setInicio(LocalDate inicio) {
        this.inicio = inicio;
    }

    public LocalDate getFim() {
        return fim;
    }

    public void setFim(LocalDate fim) {
        this.fim = fim;
    }

    public String getAssunto() {
        return assunto;
    }

    public void setAssunto(String assunto) {
        this.assunto = assunto;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    public List<LocalDate> getDiasDocente1() {
        return diasDocente1;
    }

    public List<LocalDate> getDiasDocente2() {
        return diasDocente2;
    }

    public void setDiasDocente1(List<LocalDate> diasDocente1) {
        this.diasDocente1 = diasDocente1;
    }

    public void setDiasDocente2(List<LocalDate> diasDocente2) {
        this.diasDocente2 = diasDocente2;
    }

    public boolean contemDocente(Docente docente) {
        return (docente1 != null && docente1.equals(docente)) ||
                (docente2 != null && docente2.equals(docente));
    }

    public static String getDataFormatada(LocalDate data) {
        return data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }


    @Override
    public String toString() {
        String init = inicio.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String finish = fim.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        String prof = docente1 == null ? "\n\t- Não há professores cadastrados!" :
                    "\n\t- Professores: "+ docente1.getNome() + (docente2 == null ? "" : " e "+docente2.getNome());

        return "\n\t- Semana: " + init + " a "+ finish + "\n\t- Assunto: "+ assunto +
                prof + "\n\t--------------------------------------";
    }

}
