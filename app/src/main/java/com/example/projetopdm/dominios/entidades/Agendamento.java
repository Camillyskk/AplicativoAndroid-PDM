package com.example.projetopdm.dominios.entidades;

import java.io.Serializable;

public class Agendamento extends Procedimento implements Serializable {

    public int ID, usuarios_id, procedimento_id;
    public String dia, hora, procedimento;
    public Double valor;
}
