package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetopdm.dominios.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {


    private SQLiteDatabase conexao;
    public UsuarioRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Usuario usuario){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", usuario.email);
        contentValues.put("Senha", usuario.senha);

        conexao.insertOrThrow("Usuario", null, contentValues);
    }

    public void excluir(String email){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(email);

        conexao.delete("Usuario", "email = ?", parametros);
    }

    public void alterar(Usuario usuario){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Email", usuario.email);
        contentValues.put("Senha", usuario.senha);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(usuario.email);

        conexao.update("Usuario", contentValues, "Email = ?", parametros);
    }

    public List<Usuario> buscarUsuarios() {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Email, Senha ");
        sql.append("FROM Usuario ");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Usuario usuario = new Usuario();

                usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
                usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));

                usuarios.add(usuario);

            } while (resultado.moveToNext());
        }
        return null;
    }

    public Usuario buscarUsuario(String email){

        Usuario usuario = new Usuario();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECET Nome, Sobrenome, Telefone, Cidade, Usuario_Email ");
        sql.append("FROM usuario ");
        sql.append("WHERE ID = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(email);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
            usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));

            return usuario;
        }
        return null;
    }
}
