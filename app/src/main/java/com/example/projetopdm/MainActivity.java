package com.example.projetopdm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projetopdm.clinica.Agenda;
import com.example.projetopdm.usuarios.Usuario;

import com.example.projetopdm.database.Conexao;


public class MainActivity extends AppCompatActivity {

    ConstraintLayout activity_main;


    EditText et_email, et_senha;
    Button bt_entrar, bt_cadastrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity_main = (ConstraintLayout) findViewById(R.id.activity_main);
        Conexao.criarConexao(activity_main, getBaseContext());

        bt_entrar = findViewById(R.id.entrar);
        bt_cadastrar = findViewById(R.id.bt_cadastro);

        et_email = findViewById(R.id.email);
        et_senha = findViewById(R.id.senha);

        bt_entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Usuario usuario = new Usuario();
                usuario.setEmail(et_email.getText().toString());
                usuario.setSenha(et_senha.getText().toString());

                Usuario usu = Agenda.autenticarUsuario(usuario);

                Log.d("TESTE","LC: "+usu.getEmail()+usu.getSenha());
               if (usu == null) {
                    Toast.makeText(getApplicationContext(), "Usuário não cadastrado.", Toast.LENGTH_SHORT).show();
                }
                else{
                   Intent i = new Intent(getBaseContext(), UsuarioActivity.class);
                   startActivity(i);
                }

            }
        });

        //Desabilitar editTexts, arrumar os placeholders, ajustar a altura pra funcionar
        //o scrollview certinho
        //ver a exclusão de agendamentos, pra poder tirar os items excluidos da tela
        //ver se quando clica em editar e abre a outra fragment realment vai atualizar
        //o agendamento
        //mudar o texto do AboutUs
        //verificar validações

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), CadastroActivity.class);
                startActivity(i);
            }
        });

    }
}