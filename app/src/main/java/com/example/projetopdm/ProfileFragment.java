package com.example.projetopdm;

import static com.example.projetopdm.CadastroActivity.validarCPF;
import static com.example.projetopdm.CadastroActivity.validarCidade;
import static com.example.projetopdm.CadastroActivity.validarDataNasc;
import static com.example.projetopdm.CadastroActivity.validarEmail;
import static com.example.projetopdm.CadastroActivity.validarNome;
import static com.example.projetopdm.CadastroActivity.validarRG;
import static com.example.projetopdm.CadastroActivity.validarSenha;
import static com.example.projetopdm.CadastroActivity.validarTelefone;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.database.Session;
import com.example.projetopdm.dominios.entidades.Usuarios;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;

public class ProfileFragment extends Fragment {

    EditText et_nome, et_sobrenome, et_email, et_telefone, et_cidade, et_dataNasc, et_RG, et_CPF, et_senha;
    TextView cabecalho;
    Button bt_atualizar, bt_sair, bt_deletar;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    UsuarioRepo usuarioRepo = new UsuarioRepo(conexao);
    Usuarios usuarioatual;

    private Session session;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
    }

    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        criarConexao();
        session = new Session(getContext());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_profile, container, false);

        //EditTexts com os dados
        et_nome = v.findViewById(R.id.nome);
        et_sobrenome = v.findViewById(R.id.sobrenome);
        et_email = v.findViewById(R.id.email);
        et_telefone = v.findViewById(R.id.telefone);
        et_cidade = v.findViewById(R.id.cidade);
        et_dataNasc = v.findViewById(R.id.dataNasc);
        et_RG = v.findViewById(R.id.rg);
        et_CPF = v.findViewById(R.id.cpf);
        et_senha = v.findViewById(R.id.valor);

        bt_atualizar = v.findViewById(R.id.atualizar);
        bt_sair = v.findViewById(R.id.bt_sair);
        bt_deletar = v.findViewById(R.id.bt_deletar);

        cabecalho = v.findViewById(R.id.cabecalho);

        usuarioatual = usuarioRepo.buscarUsuario(session.getEmail());

        //puxa dados do banco
        et_nome.setText(usuarioatual.nome.toString());
        et_sobrenome.setText(usuarioatual.sobrenome.toString());
        et_email.setText(usuarioatual.email.toString());
        et_telefone.setText(usuarioatual.telefone.toString());
        et_cidade.setText(usuarioatual.cidade.toString());
        et_dataNasc.setText(usuarioatual.nascimento.toString());
        et_CPF.setText(usuarioatual.CPF.toString());
        et_RG.setText(usuarioatual.RG.toString());
        et_senha.setText(usuarioatual.senha.toString());

        cabecalho.setText("Bem vindo(a) " + usuarioatual.nome);

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                Usuarios user = new Usuarios();

                user.ID = usuarioatual.ID;
                user.nome = et_nome.getText().toString();
                user.sobrenome = et_sobrenome.getText().toString();
                user.email = et_email.getText().toString();
                user.senha = et_senha.getText().toString();
                user.telefone = et_telefone.getText().toString();
                user.cidade = et_cidade.getText().toString();
                user.RG = et_RG.getText().toString();
                user.CPF = et_CPF.getText().toString();
                user.nascimento = et_dataNasc.getText().toString();

                usuarioRepo.alterar(user);

                if(et_email.isEnabled()){
                   if(isDadosValidos()) {
                    desabilitarEditTexts();
                    }
                }
                else{
                    habilitarEditTexts();
                }
            }
        });

        bt_sair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.setID(-1);
                Intent i = new Intent(getActivity(), MainActivity.class); //volta pro login
                startActivity(i);
            }
        });

        //chama o metodo mas nao aparece o builder
        bt_deletar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cria mensagem de alerta
                android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(getContext()); // 2 - Estilo do Alerta
                builder.setTitle("Exclusão de usuário");
                builder.setMessage("Você realmente deseja excluir seu registro?");
                android.app.AlertDialog dialog = builder.create();
                // Adiciona botão, se null apenas fecha o alerta
                builder.setNegativeButton("Não", null);
                // adiciona botão, evento onClick adicionado
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        usuarioRepo.excluir(usuarioatual.ID);
                        Toast.makeText(getContext(),"Registro excluído sucesso!", Toast.LENGTH_SHORT).show();
                        session.setID(-1);
                        Intent i = new Intent(getActivity(), MainActivity.class);
                        startActivity(i);
                    }
                });
                usuarioRepo.excluir(usuarioatual.ID);
                Toast.makeText(getContext(),"Registro excluído sucesso!", Toast.LENGTH_SHORT).show();
                session.setID(-1);
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });

        return v;
    }

    public void habilitarEditTexts(){
        et_nome.setEnabled(true);
        et_sobrenome.setEnabled(true);
        et_email.setEnabled(true);
        et_senha.setEnabled(true);
        et_cidade.setEnabled(true);
        et_telefone.setEnabled(true);
        et_dataNasc.setEnabled(true);
        et_CPF.setEnabled(true);
        et_RG.setEnabled(true);
    }

    public void desabilitarEditTexts (){
        et_nome.setEnabled(false);
        et_sobrenome.setEnabled(false);
        et_email.setEnabled(false);
        et_senha.setEnabled(false);
        et_cidade.setEnabled(false);
        et_telefone.setEnabled(false);
        et_dataNasc.setEnabled(false);
        et_CPF.setEnabled(false);
        et_RG.setEnabled(false);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public boolean isDadosValidos(){
        if(validarNome(et_nome) &&
           validarNome(et_sobrenome) &&
           validarEmail(et_email) &&
           validarCPF(et_CPF) &&
           validarRG(et_RG) &&
           validarEmail(et_email) &&
           validarSenha(et_senha) &&
           validarTelefone(et_telefone) &&
           validarCidade(et_cidade) &&
           validarDataNasc(et_dataNasc)){
            return true;
        }
        return false;
    }


    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getActivity());

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(activity_main, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

            usuarioRepo = new UsuarioRepo(conexao);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
    }
}