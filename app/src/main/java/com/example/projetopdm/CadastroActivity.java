package com.example.projetopdm;

import static com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo.usuarioExiste;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Usuario;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;


public class CadastroActivity extends AppCompatActivity {
    EditText et_nome, et_sobrenome, et_email, et_telefone, et_cidade, et_dataNasc, et_RG, et_CPF, et_senha;
    Button cadastrar, login;
    String nome, sobrenome, CPF, RG, cidade, telefone, nascimento, email, senha;

    LinearLayout activity_cadastro;

    SQLiteDatabase conexao;
    DadosOpenHelper dadosOpenHelper;
    Usuario usuario;
    UsuarioRepo usuarioRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        activity_cadastro = findViewById(R.id.activity_cadastro);

        criarConexao();
        usuarioRepo = new UsuarioRepo(conexao);

        et_nome = findViewById(R.id.nome);
        et_sobrenome = findViewById(R.id.sobrenome);
        et_email = findViewById(R.id.email);
        et_telefone = findViewById(R.id.telefone);
        et_cidade = findViewById(R.id.cidade);
        et_dataNasc = findViewById(R.id.dataNasc);
        et_RG = findViewById(R.id.rg);
        et_CPF = findViewById(R.id.cpf);
        et_senha = findViewById(R.id.valor);

        cadastrar = findViewById(R.id.cadastrar);
        login = findViewById(R.id.bt_login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);

            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if(validarNome(et_nome) && validarNome(et_sobrenome) && validarRG(et_RG) && validarCPF(et_CPF) && validarDataNasc(et_dataNasc) && validarEmail(et_email) && validarTelefone(et_telefone) && validarCidade(et_cidade) && validarSenha(et_senha)){
                    usuario = new Usuario();

                    nome = et_nome.getText().toString();
                    sobrenome = et_sobrenome.getText().toString();
                    email = et_email.getText().toString();
                    telefone = et_telefone.getText().toString();
                    cidade = et_cidade.getText().toString();
                    nascimento = et_dataNasc.getText().toString();
                    RG = et_RG.getText().toString();
                    CPF = et_CPF.getText().toString();
                    senha = et_senha.getText().toString();

                    usuario.nome = nome;
                    usuario.sobrenome = sobrenome;
                    usuario.telefone = telefone;
                    usuario.email = email;
                    usuario.senha = senha;
                    usuario.cidade = cidade;
                    usuario.CPF = CPF;
                    usuario.RG = RG;
                    usuario.nascimento = nascimento;

                    usuarioRepo.inserir(usuario);
                    Toast.makeText(getBaseContext(),"Usu??rio cadastrado com sucesso!", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }
        });
    }

    public static boolean validarNome(EditText editText){
        String nome = editText.getText().toString();
        if(nome.length()<1){
            editText.setError("Este campo n??o pode ficar em branco.");
            return false;
        }
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static boolean validarDataNasc(EditText editText){
        String data = editText.getText().toString();

        String dateFormat = "dd/MM/uuuu";
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(dateFormat).withResolverStyle(ResolverStyle.STRICT);

        try{
            LocalDate date = LocalDate.parse(data, dateTimeFormatter);
            return true;
        }
        catch (DateTimeParseException e){
            editText.setError("Digite uma data v??lida.");
            return false;
        }
    }

    public static boolean validarEmail(EditText editText){
        String email = editText.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editText.setError("Insira um E-mail v??lido.");
            return false;
        }
        if(usuarioExiste(email)){
            editText.setError("Este email j?? est?? sendo usado.");
            return false;
        }
        return true;
    }

    public static boolean validarSenha(EditText editText){
        String senha = editText.getText().toString();
        if(senha.length()<8){
            editText.setError("A senha precisa ter pelo menos 8 caracteres");
            return false;
        }
        return true;
    }

    public static boolean
    validarTelefone(EditText editText){
        String telefone = editText.getText().toString();
        if(telefone.length()<11){
            editText.setError("O telefone precisa ter 11 digitos, incluindo o DDD.");
            return false;
        }
        return true;
    }

    public static boolean validarCidade(EditText editText) {
        if(TextUtils.isEmpty(editText.getText().toString())){
            editText.setError("Este campo n??o pode ficar vazio!");
            return false;
        }
        return true;
    }

    public static boolean validarRG(EditText editText){
        String rg = editText.getText().toString();
        if(rg.length()<5){
            editText.setError("Digite um n??mero de RG v??lido.");
            return false;
        }
        return true;
    }

    public static boolean validarCPF(EditText editText){
        String cpf = editText.getText().toString();
        if(cpf.length()<11){
            editText.setError("Digite um n??mero de CPF v??lido e sem os pontos.");
            return false;
        }
        return true;
    }
    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getBaseContext());

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(activity_cadastro, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

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