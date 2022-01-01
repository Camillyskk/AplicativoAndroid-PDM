package com.example.projetopdm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.database.Session;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.Usuarios;
import com.example.projetopdm.dominios.entidades.repositorios.ProcedimentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout activity_main;

    EditText et_email, et_senha;
    Button bt_entrar, bt_cadastrar;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    UsuarioRepo usuarioRepo = new UsuarioRepo(conexao);
    Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        activity_main = (ConstraintLayout) findViewById(R.id.activity_main);
        //deleteDatabase("db_clinica");     //se precisar mudar mais alguma coisa na estrutura
        criarConexao();

        //descomentar na primeira vez rodar o app pra poder inserir os procedimentos
        /*ProcedimentoRepo procedimentoRepo = new ProcedimentoRepo(conexao);
        Procedimento procedimento = new Procedimento();

        procedimento.nome = "Massagem";
        procedimento.valor = 50.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Pintar o cabelo";
        procedimento.valor = 80.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Pintar as unhas";
        procedimento.valor = 40.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Esfoliação";
        procedimento.valor = 35.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Limpeza de pele";
        procedimento.valor = 120.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Maquiagem";
        procedimento.valor = 65.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Cortar o cabelo";
        procedimento.valor = 50.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Alisamento";
        procedimento.valor = 250.00;
        procedimentoRepo.inserir(procedimento);
        procedimento.nome = "Depilação";
        procedimento.valor = 70.00;
        procedimentoRepo.inserir(procedimento);*/

        bt_entrar = findViewById(R.id.bt_entrar);
        bt_cadastrar = findViewById(R.id.bt_cadastro);

        et_email = findViewById(R.id.email);
        et_senha = findViewById(R.id.valor);

        session = new Session(getBaseContext());

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = et_email.getText().toString();
                String senha = et_senha.getText().toString();

                if (usuarioRepo.validaSenha(senha, email)) {

                    session.setID(usuarioRepo.buscarUsuario(email).ID);
                    session.setEmail(usuarioRepo.buscarUsuario(email).email);

                    Log.d("EMAIL ", session.getEmail());

                    Intent i = new Intent(getBaseContext(), UsuarioActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
                }

            }
        });

        //Colocar os dados do banco como placeholder nos EditTexts
        //ver a exclusão de agendamentos, pra poder tirar os items excluidos da tela
        //ver se quando clica em editar e abre a outra fragment realment vai atualizar
        //o agendamento

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CadastroActivity.class);
                startActivity(i);
            }
        });

    }


    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(this);

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(activity_main, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

            usuarioRepo = new UsuarioRepo(conexao);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
    }
}