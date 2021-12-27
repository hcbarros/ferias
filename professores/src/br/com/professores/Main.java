package br.com.professores;

import br.com.professores.enums.Assunto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

public class Main {

    public static void main(String[] args) {

        int count = 0;
        for(int dia = 0; dia < 365; dia++) {
            LocalDate l = LocalDate.of(2022,1,3);

            LocalDate data = l.plusDays(dia);
            String dia01 = data.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

            int diaSemana = data.getDayOfWeek().getValue();
            if((diaSemana == 1 && (++count) <= 52) || (dia == 0 && diaSemana <= 5)) {
                int plus = 5 - diaSemana;
                String dia05 = data.plusDays(plus)
                                    .format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                System.out.println("Turma A na semana do dia "+ dia01 + " até " + dia05 + " terá C#");
            }
        }

    }
}
