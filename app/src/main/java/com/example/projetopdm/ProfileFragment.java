package com.example.projetopdm;

import static com.example.projetopdm.CadastroActivity.validarCPF;
import static com.example.projetopdm.CadastroActivity.validarCidade;
import static com.example.projetopdm.CadastroActivity.validarDataNasc;
import static com.example.projetopdm.CadastroActivity.validarEmail;
import static com.example.projetopdm.CadastroActivity.validarNome;
import static com.example.projetopdm.CadastroActivity.validarRG;
import static com.example.projetopdm.CadastroActivity.validarSenha;
import static com.example.projetopdm.CadastroActivity.validarTelefone;
import static com.example.projetopdm.MainActivity.usuarioatual;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.projetopdm.clinica.Agenda;
import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Usuarios;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;
import com.example.projetopdm.usuarios.Usuario;
import com.google.android.material.snackbar.Snackbar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    EditText et_nome, et_sobrenome, et_email, et_telefone, et_cidade, et_dataNasc, et_RG, et_CPF, et_senha;
    Button bt_atualizar, bt_sair;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    UsuarioRepo usuarioRepo = new UsuarioRepo(conexao);


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
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
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
        et_senha = v.findViewById(R.id.senha);


        bt_atualizar = v.findViewById(R.id.atualizar);
        bt_sair = v.findViewById(R.id.bt_sair);


        Log.d("ID: ", toString().valueOf(usuarioatual.ID));
        Log.d("Cidade: ", usuarioatual.cidade.toString());
        Log.d("Nome: ", usuarioatual.nome.toString());
        Log.d("Sobrenome: ", usuarioatual.sobrenome.toString());
        Log.d("CPF: ", usuarioatual.CPF.toString());
        Log.d("RG: ", usuarioatual.RG.toString());
        Log.d("Telefone: ", usuarioatual.telefone.toString());
        Log.d("Nascimento: ", usuarioatual.nascimento.toString());
        Log.d("Email: ", usuarioatual.email.toString());
        Log.d("Senha: ", usuarioatual.senha.toString());


        et_nome.setText(usuarioatual.nome.toString());
        et_sobrenome.setText(usuarioatual.sobrenome.toString());
        et_email.setText(usuarioatual.email.toString());
        et_telefone.setText(usuarioatual.telefone.toString());
        et_cidade.setText(usuarioatual.cidade.toString());
        et_dataNasc.setText(usuarioatual.nascimento.toString());
        et_CPF.setText(usuarioatual.CPF.toString());
        et_RG.setText(usuarioatual.RG.toString());
        et_senha.setText(usuarioatual.senha.toString());


        //Desabilitar os EditTexts
        //desabilitarEditTexts();

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
                Intent i = new Intent(getActivity(), MainActivity.class); //volta pro login
                startActivity(i);
            }
        });

        return v;
    }

    public void setInfoProfile(Usuario usuario){
        int posicao = 0;
        for(Usuario usuarioProcurado : Agenda.usuarios){
            if(usuarioProcurado.getEmail() == usuario.getEmail()){
                posicao = Agenda.usuarios.indexOf(usuario);
            }
        }
        et_email.setText(Agenda.usuarios.get(posicao).getEmail());
        et_senha.setText(Agenda.usuarios.get(posicao).getSenha());
        et_telefone.setText(Agenda.usuarios.get(posicao).getTelefone());
        et_cidade.setText(Agenda.usuarios.get(posicao).getCidade());
        //ajustar pra puxar os dados do banco
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