package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetopdm.dominios.entidades.Procedimento;

import java.util.ArrayList;
import java.util.List;

public class ProcedimentoRepo {
    private SQLiteDatabase conexao;
    public ProcedimentoRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Procedimento procedimento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", procedimento.nome);
        contentValues.put("Valor", procedimento.valor);

        conexao.insertOrThrow("Procedimento", "ID", contentValues);
    }

    public void excluir(int id){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("Procedimento", "ID = ?", parametros);
    }

    public void alterar(Procedimento procedimento){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", procedimento.nome);
        contentValues.put("Valor", procedimento.valor);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(procedimento.ID);

        conexao.update("Procedimento", contentValues, "ID = ?", parametros);
    }

    public List<Procedimento> buscarProcedimentos() {

        List<Procedimento> procedimentos = new ArrayList<Procedimento>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Nome, Valor ");
        sql.append("FROM Procedimento ");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Procedimento procedimento = new Procedimento();

                procedimento.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                procedimento.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
                procedimento.valor = resultado.getDouble(resultado.getColumnIndexOrThrow("Valor"));

                procedimentos.add(procedimento);

            } while (resultado.moveToNext());
        }
        return null;
    }

    public List<String> spinnerProcedimentos() {

        List<String> procedimentos = new ArrayList<String>();

        Cursor resultado = conexao.rawQuery("SELECT Nome FROM Procedimento", null);
        resultado.moveToFirst();

        do {
            procedimentos.add(resultado.getString(resultado.getColumnIndexOrThrow("Nome")));
        } while (resultado.moveToNext());

        return procedimentos;
    }


    public Procedimento buscarProcedimentoNome(String nome){

        Procedimento procedimento = new Procedimento();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * ");
        sql.append("FROM Procedimento ");
        sql.append("WHERE Nome = '" + nome + "'");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            procedimento.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
            procedimento.valor = resultado.getDouble(resultado.getColumnIndexOrThrow("Valor"));
            procedimento.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));

            return procedimento;
        }
        return null;
    }

    public Procedimento buscarProcedimentoID(int id){

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT * FROM Procedimento WHERE ID=?");

        String[] parametros = new String[]{String.valueOf(id)};

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {
            Procedimento procedimento = new Procedimento();
            resultado.moveToFirst();

            procedimento.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
            procedimento.valor = resultado.getDouble(resultado.getColumnIndexOrThrow("Valor"));
            procedimento.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));

            return procedimento;
        }
        return null;
    }
}
