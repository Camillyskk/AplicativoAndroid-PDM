package com.example.projetopdm.clinica;

import java.util.ArrayList;

public class Horario {
    public static ArrayList<String> listaHorarios = new ArrayList<>();

    public static void setHorariosPadrao(){
        listaHorarios.add("09:00");
        listaHorarios.add("09:30");
        listaHorarios.add("10:00");
        listaHorarios.add("10:30");
        listaHorarios.add("11:00");
        listaHorarios.add("11:30");
        listaHorarios.add("13:00");
        listaHorarios.add("13:30");
        listaHorarios.add("14:00");
        listaHorarios.add("14:30");
        listaHorarios.add("15:00");
        listaHorarios.add("15:30");
        listaHorarios.add("16:00");
        listaHorarios.add("16:30");
        listaHorarios.add("17:00");
        listaHorarios.add("17:30");
        listaHorarios.add("18:00");
        listaHorarios.add("18:30");
        listaHorarios.add("19:00");
    }

    public static void printHorarios() {
        for (int i = 0; i < listaHorarios.size(); i++) {
            System.out.println(listaHorarios.get(i));
        }
    }
}
