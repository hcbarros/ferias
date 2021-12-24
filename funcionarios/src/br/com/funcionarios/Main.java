package br.com.funcionarios;

import br.com.funcionarios.tipos_funcionario.*;
import br.com.funcionarios.utils.MenuPrincipal;

import java.util.ArrayList;
import java.util.List;


public class Main {

    private static List<Funcionario> funcionarios = new ArrayList<>();

    static {
        Colaborador f1 = new Colaborador("Alex Santos", "1234", 1500);
        LiderTecnico f2 = new LiderTecnico("Ana Luíza", "2345", 5000);
        GerenteDepartamento f3 = new GerenteDepartamento("José Rodrigues", "3456", 10000);
        GerenteGeral f4 = new GerenteGeral("Leandro Silva", "4567", 15000);

        funcionarios.addAll(List.of(f1,f2,f3,f4));
    }


    public static void main(String[] args) {
        MenuPrincipal.menu(funcionarios);
    }
}
