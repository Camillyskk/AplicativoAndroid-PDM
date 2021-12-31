package com.example.projetopdm.clinica;

import android.util.Log;

import com.example.projetopdm.usuarios.Usuario;


import java.util.ArrayList;

public class Agenda extends Horario {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Horario> listaHorarios = new ArrayList<>();
    public static ArrayList<Procedimentos> listaProcedimentos = new ArrayList<>();
    public static ArrayList<Agendamentos> agendamentos = new ArrayList<>();

    public static void carregarUsuariosDoBanco() {
//
//        Agenda agenda = new Agenda();
        Horario.setHorariosPadrao();

        Usuario usuario = new Usuario();
        usuario.setNome("Guilherme");
        usuario.setSobrenome("Borges");
        usuario.setEmail("usuario@user.com");
        usuario.setSenha("adm");
        usuarios.add(usuario);

    }


}
