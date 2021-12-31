package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.projetopdm.dominios.entidades.Usuarios;

import java.util.ArrayList;
import java.util.List;

public class UsuarioRepo {

    public SQLiteDatabase conexao;

    public UsuarioRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

   public void inserir(Usuarios usuarios){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", usuarios.nome);
       contentValues.put("Sobrenome", usuarios.sobrenome);
       contentValues.put("Telefone", usuarios.telefone);
       contentValues.put("Cidade", usuarios.cidade);
       contentValues.put("CPF", usuarios.CPF);
       contentValues.put("RG", usuarios.RG);
       contentValues.put("Nascimento", usuarios.nascimento);
       contentValues.put("Email", usuarios.email);
       contentValues.put("Senha", usuarios.senha);
       conexao.insertOrThrow("Usuarios", "ID", contentValues);
   }

   public void excluir(int id){

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(id);

       conexao.delete("Usuarios", "ID = ?", parametros);
   }

   public void alterar(Usuarios usuarios){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", usuarios.nome);
       contentValues.put("Sobrenome", usuarios.sobrenome);
       contentValues.put("Telefone", usuarios.telefone);
       contentValues.put("Cidade", usuarios.cidade);
       contentValues.put("CPF", usuarios.CPF);
       contentValues.put("RG", usuarios.RG);
       contentValues.put("Nascimento", usuarios.nascimento);
       contentValues.put("Email", usuarios.email);
       contentValues.put("Senha", usuarios.senha);

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(usuarios.ID);

       conexao.update("Usuario", contentValues, "ID = ?", parametros);
   }

   public List<Usuarios> buscarUsuarios() {

        List<Usuarios> usuarios = new ArrayList<Usuarios>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT * ");
        //sql.append("SELECT Nome, Sobrenome, Telefone, Cidade, CPF, RG, Nascimento, Email, Senha ");
        sql.append("FROM Usuarios ");

       Log.d("TESTE","ANTES DO RESULTADO");
        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();
            Log.d("TESTE","DEPOIS DO MOVE TO FIRST");

            do {
                Usuarios usuario = new Usuarios();

                //usuario.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                usuario.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
                usuario.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
                usuario.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
                usuario.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
                usuario.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
                usuario.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
                usuario.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
                usuario.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
                usuario.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));

                Log.d("TESTE","ANTES DE ADD");
                usuarios.add(usuario);

            } while (resultado.moveToNext());

            //return usuarios;
        }
       Log.d("TESTE","ANTES DO RETORNO");
       return null;
   }

   public Usuarios buscarUsuario(String email, String senha){

        //Usuarios usuarios = new Usuarios();

       StringBuilder sql = new StringBuilder();
       sql.append("SELECT * FROM Usuarios WHERE Email=? AND Senha=? ");

       String[] parametros = new String[]{email, senha};

       Log.d("TESTE","ANTES CURSOR");
       Cursor resultado = conexao.rawQuery(sql.toString(), parametros);
       Log.d("TESTE","DEPOIS CURSOR");

       Usuarios usuarios = new Usuarios();

       if (resultado.moveToNext()) {
           usuarios.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
           usuarios.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
           usuarios.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
           usuarios.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
           usuarios.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
           usuarios.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
           usuarios.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
           usuarios.email = resultado.getString(resultado.getColumnIndexOrThrow("Email"));
           usuarios.senha = resultado.getString(resultado.getColumnIndexOrThrow("Senha"));
           usuarios.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
       }

       return usuarios;
   }

    public boolean validaSenha(String senha, String email) {

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Usuarios WHERE Email = '" + email + "' AND Senha = '" + senha + "'");
        //sql.append("SELECT * FROM Usuarios");

        String[] parametros = new String[]{senha, email};

        Cursor resultado = conexao.rawQuery(sql.toString(),null);
        Log.d("AAAAAAAAAAAAAAAA", String.valueOf(resultado.getCount()));
        //resultado.moveToFirst();

        // Retorna se o cursor tem 1 resultado com o email e senha informados
        return resultado != null && resultado.getCount() > 0;

    }
}
