package com.example.projetopdm;

import static com.example.projetopdm.CadastroActivity.validarCPF;
import static com.example.projetopdm.CadastroActivity.validarCidade;
import static com.example.projetopdm.CadastroActivity.validarDataNasc;
import static com.example.projetopdm.CadastroActivity.validarEmail;
import static com.example.projetopdm.CadastroActivity.validarNome;
import static com.example.projetopdm.CadastroActivity.validarRG;
import static com.example.projetopdm.CadastroActivity.validarSenha;
import static com.example.projetopdm.CadastroActivity.validarTelefone;

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

import com.example.projetopdm.clinica.Agenda;
import com.example.projetopdm.database.DadosOpenHelper;
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


    UsuarioRepo usuarioRepo;


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
        et_email = v.findViewById(R.id.email);
        et_senha = v.findViewById(R.id.senha);
        et_telefone = v.findViewById(R.id.telefone);
        et_cidade = v.findViewById(R.id.cidade);
        et_dataNasc = v.findViewById(R.id.dataNasc);
        et_RG = v.findViewById(R.id.rg);
        et_CPF = v.findViewById(R.id.cpf);
        et_senha = v.findViewById(R.id.senha);

        bt_atualizar = v.findViewById(R.id.atualizar);
        bt_sair = v.findViewById(R.id.bt_sair);

        //Desabilitar os EditTexts
        //desabilitarEditTexts();

        /*
            et_cidade.setText(usuarioatual.cidade);
            et_nome.setText(usuarioatual.nome);
            et_sobrenome.setText(usuarioatual.sobrenome);
            et_CPF.setText(usuarioatual.CPF);
            et_RG.setText(usuarioatual.RG);
            et_dataNasc.setText(usuarioatual.nascimento);
            et_email.setText(usuarioatual.email);
            et_senha.setText(usuarioatual.senha);
         */


        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
//                puxar dados do banco e colocar no text dos editText

                /*
                usuarioRepo.alterar(usuarioatual);
                */


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


}