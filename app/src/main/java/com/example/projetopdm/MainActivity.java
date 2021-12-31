package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.CharArrayBuffer;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopdm.database.Conexao;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout activity_main;

    EditText et_email, et_senha;
    Button bt_entrar, bt_cadastrar;
    UsuarioRepo usuarioRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (ConstraintLayout) findViewById(R.id.activity_main);
        //deleteDatabase("db_clinica");     //se precisar mudar mais alguma coisa na estrutura
        Conexao.criarConexao(activity_main, getBaseContext());

        bt_entrar = findViewById(R.id.bt_entrar);
        bt_cadastrar = findViewById(R.id.bt_cadastro);

        et_email = findViewById(R.id.email);
        et_senha = findViewById(R.id.senha);

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //List<Usuarios> usuarios = new ArrayList<Usuarios>();
                //Usuarios usuario;

                String email =  et_email.getText().toString();
                String senha = et_senha.getText().toString();

                Log.d("Email: ",email);
                Log.d("Senha: ",senha);

                //StringBuilder sql = new StringBuilder();
                //sql.append("SELECT * FROM Usuarios WHERE Email = '" + email + "' AND Senha = '" + senha + "'");
                //Log.d("TESTE",sql.toString());


                if (usuarioRepo.validaSenha(senha, email)){
                    Log.d("TESTE","LOGADO COM SUCESSO");
                    Intent i = new Intent(getBaseContext(), UsuarioActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
                    Log.d("TESTE","FALHA NO LOGIN");
                }



                /*if (usuario.senha.equals(senha)) {
                    //return usuarioProcurado;
                    Log.d("TESTE","LOGADO COM SUCESSO");

                    Intent i = new Intent(getBaseContext(), UsuarioActivity.class);
                    startActivity(i);
                }*/

                //Integer cont = usuarioRepo.buscarUsuarios().size();

                /*for (int a = 0; a < cont; a++){
                    Log.d("TESTE","AAAAAAAAAAAAAAA");
                    usuarios.set(a, usuarioRepo.buscarUsuario(a));
                }*/
                /*for (Usuarios usuarioProcurado : usuarios) {
                    Log.d("TESTE","LA: "+usuarioProcurado.email+usuarioProcurado.senha);
                    if (usuarioProcurado.email.equals(email) && usuarioProcurado.senha.equals(senha)) {
                        //return usuarioProcurado;
                        Log.d("TESTE","LOGADO COM SUCESSO");

                        Intent i = new Intent(getBaseContext(), UsuarioActivity.class);
                        startActivity(i);
                    }
                }*/
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



}