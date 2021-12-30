package com.example.projetopdm.database;

import static android.app.PendingIntent.getActivity;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.projetopdm.MainActivity;
import com.example.projetopdm.R;
import com.google.android.material.snackbar.Snackbar;

public class Conexao{

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    public static Conexao criarConexao(ConstraintLayout activity, Context context) {
        try {
            dadosOpenHelper = new DadosOpenHelper(activity.getContext());

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(activity, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(context);
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
        return null;
    }

}
