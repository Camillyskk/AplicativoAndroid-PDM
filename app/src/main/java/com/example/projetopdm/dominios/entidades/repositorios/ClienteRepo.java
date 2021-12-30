package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetopdm.dominios.entidades.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteRepo {

    private SQLiteDatabase conexao;
    public ClienteRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

   public void inserir(Cliente cliente){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", cliente.nome);
       contentValues.put("Sobrenome", cliente.nome);
       contentValues.put("Telefone", cliente.nome);
       contentValues.put("Cidade", cliente.nome);
       contentValues.put("CPF", cliente.nome);
       contentValues.put("RG", cliente.nome);
       contentValues.put("Nascimento", cliente.nome);
       contentValues.put("Usuario_Email", cliente.usuario_email);

       conexao.insertOrThrow("Cliente", "ID", contentValues);
   }

   public void excluir(int id){

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(id);

       conexao.delete("Cliente", "ID = ?", parametros);
   }

   public void alterar(Cliente cliente){

       ContentValues contentValues = new ContentValues();
       contentValues.put("Nome", cliente.nome);
       contentValues.put("Sobrenome", cliente.nome);
       contentValues.put("Telefone", cliente.nome);
       contentValues.put("Cidade", cliente.nome);
       contentValues.put("CPF", cliente.nome);
       contentValues.put("RG", cliente.nome);
       contentValues.put("Nascimento", cliente.nome);
       contentValues.put("Usuario_Email", cliente.usuario_email);

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(cliente.ID);

       conexao.update("Cliente", contentValues, "ID = ?", parametros);
   }

   public List<Cliente> buscarClientes() {

        List<Cliente> clientes = new ArrayList<Cliente>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Nome, Sobrenome, Telefone, Cidade, CPF, RG, Nascimento, Usuario_Email ");
        sql.append("FROM Cliente ");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Cliente cli = new Cliente();

                cli.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                cli.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
                cli.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
                cli.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
                cli.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
                cli.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
                cli.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
                cli.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
                cli.usuario_email = resultado.getString(resultado.getColumnIndexOrThrow("Usuario_Email"));

                clientes.add(cli);

            } while (resultado.moveToNext());
        }
       return null;
   }

   public Cliente buscarCliente(int id){

       Cliente cli = new Cliente();

       StringBuilder sql = new StringBuilder();
       sql.append("SELECET Nome, Sobrenome, Telefone, Cidade, CPF, RG, Nascimento, Usuario_Email ");
       sql.append("FROM Cliente ");
       sql.append("WHERE ID = ?");

       String[] parametros = new String[1];
       parametros[0] = String.valueOf(id);

       Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

       if (resultado.getCount() > 0) {

           resultado.moveToFirst();

           cli.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
           cli.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
           cli.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
           cli.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
           cli.RG = resultado.getString(resultado.getColumnIndexOrThrow("RG"));
           cli.CPF = resultado.getString(resultado.getColumnIndexOrThrow("CPF"));
           cli.nascimento = resultado.getString(resultado.getColumnIndexOrThrow("Nascimento"));
           cli.usuario_email = resultado.getString(resultado.getColumnIndexOrThrow("Usuario_Email"));
           cli.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));

           return cli;
       }
       return null;
   }

}
