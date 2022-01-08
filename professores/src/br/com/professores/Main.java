package br.com.professores;

import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;
import br.com.professores.utils.Cadastro;
import br.com.professores.utils.MenuPrincipal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Main {

    private static List<Turma> turmas;
    private static List<Docente> docentes;


    static {    //INICIALIZANDO AS LISTAS

        turmas = new ArrayList<>();
        docentes = new ArrayList<>();

        Turma t1 = new Turma("aaa", "Turma A",
                45, LocalDate.of(2020,11,3));
        Turma t2 = new Turma("bbb","Turma B",
                20, LocalDate.of(2021,6,3));

        Docente d1 = new Docente("123", "Alex José");
        Docente d2 = new Docente("234", "Fernando Soares");
        Docente d3 = new Docente("345", "José Francisco");
        Docente d4 = new Docente("456", "Andréa Medeiros");

        turmas.addAll(List.of(t1,t2));
        docentes.addAll(List.of(d1,d2,d3,d4));

        Cadastro.definirSemanas(turmas.get(0));
        Cadastro.definirSemanas(turmas.get(1));
    }


    public static void main(String[] args) {
        MenuPrincipal.menu(turmas, docentes);
    }
}
