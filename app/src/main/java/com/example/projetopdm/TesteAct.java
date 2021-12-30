package com.example.projetopdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Usuarios;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;
import com.example.projetopdm.utilidadesadaptador.TesteAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class TesteAct extends AppCompatActivity {

    LinearLayout activity_teste;

    SQLiteDatabase conexao;
    DadosOpenHelper dadosOpenHelper;

    UsuarioRepo usuarioRepo;
    TesteAdapter testeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        criarConexao();


        RecyclerView lstDados = (RecyclerView) findViewById(R.id.lstDados);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        lstDados.setLayoutManager(linearLayoutManager);

        usuarioRepo = new UsuarioRepo(conexao); //cria instancia
        List<Usuarios> dados = usuarioRepo.buscarUsuarios(); //chama a funcao
        testeAdapter = new TesteAdapter(dados); //passa pro adapter
        lstDados.setAdapter(testeAdapter); //vincula ao recycler view

    }
    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getBaseContext());

            conexao = dadosOpenHelper.getWritableDatabase();

            Snackbar.make(activity_teste, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

            usuarioRepo = new UsuarioRepo(conexao);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(getBaseContext());
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
    }
}