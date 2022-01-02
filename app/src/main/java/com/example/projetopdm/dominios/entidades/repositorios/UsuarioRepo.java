package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetopdm.dominios.entidades.Usuario;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {

    public static SQLiteDatabase conexao;

    public UsuarioRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

   public void inserir(Usuario usuario){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", usuario.nome);
       contentValues.put("Sobrenome", usuario.sobrenome);
       contentValues.put("Telefone", usuario.telefone);
       contentValues.put("Cidade", usuario.cidade);
       contentValues.put("CPF", usuario.CPF);
       contentValues.put("RG", usuario.RG);
       contentValues.put("Nascimento", usuario.nascimento);
       contentValues.put("Email", usuario.email);
       contentValues.put("Senha", usuario.senha);
       conexao.insertOrThrow("Usuario", "ID", contentValues);
   }

   public void excluir(int id){

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(id);

       conexao.delete("Usuario", "ID = ?", parametros);
   }

   public void alterar(Usuario usuario){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", usuario.nome);
       contentValues.put("Sobrenome", usuario.sobrenome);
       contentValues.put("Telefone", usuario.telefone);
       contentValues.put("Cidade", usuario.cidade);
       contentValues.put("CPF", usuario.CPF);
       contentValues.put("RG", usuario.RG);
       contentValues.put("Nascimento", usuario.nascimento);
       contentValues.put("Email", usuario.email);
       contentValues.put("Senha", usuario.senha);

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(usuario.ID);

       conexao.update("Usuario", contentValues, "ID = ?", parametros);
   }

   public List<Usuario> buscarUsuario() {

        List<Usuario> usuarios = new ArrayList<Usuario>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * FROM Usuario");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();
            do {
                Usuario usuario = new Usuario();

                usuario.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                usuario.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
                usuario.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
                usuario.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
                usuario.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
                usuario.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
                usuario.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
                usuario.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
                usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
                usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));

                usuarios.add(usuario);

            } while (resultado.moveToNext());

        }
       return null;
   }

   public Usuario buscarUsuario(String email){

       StringBuilder sql = new StringBuilder();
       sql.append("SELECT * FROM Usuario WHERE Email=? ");

       String[] parametros = new String[]{email};

       Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

       Usuario usuario = new Usuario();

       if (resultado.moveToNext()) {
           usuario.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
           usuario.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
           usuario.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
           usuario.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
           usuario.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
           usuario.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
           usuario.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
           usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
           usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));
           usuario.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
       }

       return usuario;
   }

    public static boolean usuarioExiste(String email){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Usuario WHERE Email=? ");

        String[] parametros = new String[]{email};

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 1){
            return true;
        }else{
            return false;
        }
    }

    /*public Usuario buscarUsuarioID(int id){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Usuario WHERE ID = " + id);

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {
            resultado.moveToFirst();

            Usuario usuario = new Usuario();

            usuario.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
            usuario.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
            usuario.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
            usuario.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
            usuario.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
            usuario.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
            usuario.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
            usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
            usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));
            usuario.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));

            return usuario;
        }
        return null;
    }*/

    public boolean validaSenha(String senha, String email) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Usuario WHERE Senha=? AND Email=?");

        String[] parametros = new String[]{senha, email};

        Cursor resultado = conexao.rawQuery(sql.toString(),parametros);

        // Retorna se o cursor tem 1 resultado com o email e senha informados
        return resultado != null && resultado.getCount() > 0;
    }

}
