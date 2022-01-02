package com.example.projetopdm.adaptador;

import com.example.projetopdm.dominios.entidades.Agendamento;

public class PassaDados {
    static Agendamento agendamento;

    public static void setAgendamento(Agendamento agendamento) {
        PassaDados.agendamento = agendamento;
    }

    public static Agendamento getAgendamento() {
        return agendamento;
    }
}
