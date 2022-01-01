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


                //INICIALIZANDO AS LISTAS
    static {
        turmas = new ArrayList<>();
        docentes = new ArrayList<>();

        Turma t1 = new Turma("aaa", "Turma A",
                45, LocalDate.of(2020,11,2));
        Turma t2 = new Turma("bbb","Turma B",
                20, LocalDate.of(2021,11,3));

        Docente d1 = new Docente("123", "Alex José");
        Docente d2 = new Docente("234", "Fernando Soares");
        Docente d3 = new Docente("345", "José Francisco");
        Docente d4 = new Docente("456", "Andréa Medeiros");

        turmas.addAll(List.of(t1,t2));
        docentes.addAll(List.of(d1,d2,d3,d4));

        for (int i = 0; i < 4; i++) {
            if(i < 2) {
                Cadastro.definirSemanas(turmas.get(i));
                Cadastro.addProfessor(turmas.get(i), docentes.get(i));
            }
            else {
                Cadastro.addProfessor(turmas.get(i - 2), docentes.get(i));
            }
        }

    }


    public static void main(String[] args) {
        MenuPrincipal.menu(turmas, docentes);
    }
}
