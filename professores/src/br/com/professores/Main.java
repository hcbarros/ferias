package br.com.professores;


import br.com.professores.tipos.Docente;
import br.com.professores.tipos.Turma;
import br.com.professores.utils.MenuPrincipal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static Map<Turma, List<Docente>> map = new HashMap<>();

    static {
        map.put(null, new ArrayList<>());
    }


    public static void main(String[] args) {

        MenuPrincipal.menu(map);
    }
}
