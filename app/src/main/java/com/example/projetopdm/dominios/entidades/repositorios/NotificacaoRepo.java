package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetopdm.dominios.entidades.Notificacao;

import java.util.ArrayList;
import java.util.List;

public class NotificacaoRepo {

    private SQLiteDatabase conexao;
    public NotificacaoRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(Notificacao notificacao){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Dia_old", notificacao.dia_old);
        contentValues.put("Hora_old", notificacao.hora_old);
        contentValues.put("Dia_new", notificacao.dia_new);
        contentValues.put("Hora_new", notificacao.hora_new);
        contentValues.put("Cliente_ID", notificacao.cliente_id);

        conexao.insertOrThrow("Notificacao", "ID", contentValues);
    }

    public void excluir(int id){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("Notificacao", "ID = ?", parametros);
    }

    public void alterar(Notificacao notificacao){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Dia_old", notificacao.dia_old);
        contentValues.put("Hora_old", notificacao.hora_old);
        contentValues.put("Dia_new", notificacao.dia_new);
        contentValues.put("Hora_new", notificacao.hora_new);
        contentValues.put("Cliente_ID", notificacao.cliente_id);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(notificacao.ID);

        conexao.update("Notificacao", contentValues, "ID = ?", parametros);
    }

    public List<Notificacao> buscarNotificacaos() {

        List<Notificacao> notificacaos = new ArrayList<Notificacao>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT Dia_new, Hora_new, Dia_old, Hora_old ");
        sql.append("FROM Notificacao ");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                Notificacao notificacao = new Notificacao();

                notificacao.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                notificacao.cliente_id = resultado.getInt(resultado.getColumnIndexOrThrow("Cliente_ID"));
                notificacao.dia_old = resultado.getString(resultado.getColumnIndexOrThrow("Dia_old"));
                notificacao.hora_old = resultado.getString(resultado.getColumnIndexOrThrow("Hora_old"));
                notificacao.dia_new = resultado.getString(resultado.getColumnIndexOrThrow("Dia_new"));
                notificacao.hora_new = resultado.getString(resultado.getColumnIndexOrThrow("Hora_new"));

                notificacaos.add(notificacao);

            } while (resultado.moveToNext());
        }
        return null;
    }

    public Notificacao buscarNotificacao(int id){

        Notificacao notificacao = new Notificacao();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECT Dia_new, Hora_new, Dia_old, Hora_old ");
        sql.append("FROM Notificacao ");
        sql.append("WHERE ID = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            notificacao.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
            notificacao.cliente_id = resultado.getInt(resultado.getColumnIndexOrThrow("Cliente_ID"));
            notificacao.dia_old = resultado.getString(resultado.getColumnIndexOrThrow("Dia_old"));
            notificacao.hora_old = resultado.getString(resultado.getColumnIndexOrThrow("Hora_old"));
            notificacao.dia_new = resultado.getString(resultado.getColumnIndexOrThrow("Dia_new"));
            notificacao.hora_new = resultado.getString(resultado.getColumnIndexOrThrow("Hora_new"));

            return notificacao;
        }
        return null;
    }
}
