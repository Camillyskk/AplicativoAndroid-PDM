package com.example.projetopdm.dominios.entidades.repositorios;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.projetopdm.dominios.entidades.ADM;

import java.util.ArrayList;
import java.util.List;

public class ADMRepo {

    private SQLiteDatabase conexao;
    public ADMRepo(SQLiteDatabase conexao){
        this.conexao = conexao;
    }

    public void inserir(ADM adm){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", adm.nome);
        contentValues.put("Sobrenome", adm.nome);
        contentValues.put("Telefone", adm.nome);
        contentValues.put("Cidade", adm.nome);
        contentValues.put("Usuario_Email", adm.usuario_email);

        conexao.insertOrThrow("ADM", "ID", contentValues);
    }

    public void excluir(int id){

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        conexao.delete("ADM", "ID = ?", parametros);
    }

    public void alterar(ADM adm){

        ContentValues contentValues = new ContentValues();
        contentValues.put("Nome", adm.nome);
        contentValues.put("Sobrenome", adm.nome);
        contentValues.put("Telefone", adm.nome);
        contentValues.put("Cidade", adm.nome);
        contentValues.put("Usuario_Email", adm.usuario_email);

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(adm.ID);

        conexao.update("adm", contentValues, "ID = ?", parametros);
    }

    public List<ADM> buscarAdms() {

        List<ADM> adms = new ArrayList<ADM>();

        StringBuilder sql = new StringBuilder();

        sql.append("SELECET Nome, Sobrenome, Telefone, Cidade, Usuario_Email ");
        sql.append("FROM ADM ");

        Cursor resultado = conexao.rawQuery(sql.toString(), null);

        if (resultado.getCount() > 0){
            resultado.moveToFirst();

            do {
                ADM adm = new ADM();

                adm.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));
                adm.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
                adm.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
                adm.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
                adm.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
                adm.usuario_email = resultado.getString(resultado.getColumnIndexOrThrow("Usuario_Email"));

                adms.add(adm);

            } while (resultado.moveToNext());
        }
        return null;
    }

    public ADM buscarAdm(int id){

        ADM adm = new ADM();

        StringBuilder sql = new StringBuilder();
        sql.append("SELECET Nome, Sobrenome, Telefone, Cidade, Usuario_Email ");
        sql.append("FROM ADM ");
        sql.append("WHERE ID = ?");

        String[] parametros = new String[1];
        parametros[0] = String.valueOf(id);

        Cursor resultado = conexao.rawQuery(sql.toString(), parametros);

        if (resultado.getCount() > 0) {

            resultado.moveToFirst();

            adm.nome = resultado.getString(resultado.getColumnIndexOrThrow("Nome"));
            adm.sobrenome = resultado.getString(resultado.getColumnIndexOrThrow("Sobrenome"));
            adm.telefone = resultado.getString(resultado.getColumnIndexOrThrow("Telefone"));
            adm.cidade = resultado.getString(resultado.getColumnIndexOrThrow("Cidade"));
            adm.usuario_email = resultado.getString(resultado.getColumnIndexOrThrow("Usuario_Email"));
            adm.ID = resultado.getInt(resultado.getColumnIndexOrThrow("ID"));

            return adm;
        }
        return null;
    }
}