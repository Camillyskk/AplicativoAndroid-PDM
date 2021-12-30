package com.example.projetopdm.clinica;

import android.util.Log;

import com.example.projetopdm.usuarios.Usuario;


import java.util.ArrayList;

public class Agenda extends Horario {
    public static ArrayList<Usuario> usuarios = new ArrayList<>();
    public static ArrayList<Horario> listaHorarios = new ArrayList<>();
    public static ArrayList<Procedimento> listaProcedimentos = new ArrayList<>();
    public static ArrayList<Agendamento> agendamentos = new ArrayList<>();

    public static void carregarUsuariosDoBanco() {
//
//        Agenda agenda = new Agenda();
        Horario.setHorariosPadrao();

        Usuario usuario = new Usuario();
        usuario.setNome("Guilherme");
        usuario.setSobrenome("Borges");
        usuario.setEmail("adm");
        usuario.setSenha("adm");
        usuarios.add(usuario);

    }


    public static Usuario autenticarUsuario(Usuario usu) {
        ///  carregar lista de usuários
        carregarUsuariosDoBanco();

        for (Usuario usuarioProcurado : usuarios) {
            Log.d("TESTE","LA: "+usuarioProcurado.getEmail()+usuarioProcurado.getSenha());
            Log.d("TESTE","LB: "+usu.getEmail()+usu.getSenha());
            if (usuarioProcurado.getEmail().equals(usu.getEmail()) && usuarioProcurado.getSenha().equals(usu.getSenha())) {
                return usuarioProcurado;
            }
        }
        return null;
    }

}
