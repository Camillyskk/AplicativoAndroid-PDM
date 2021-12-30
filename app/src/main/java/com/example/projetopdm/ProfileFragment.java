package com.example.projetopdm;

import static com.example.projetopdm.CadastroActivity.validarCidade;
import static com.example.projetopdm.CadastroActivity.validarEmail;
import static com.example.projetopdm.CadastroActivity.validarSenha;
import static com.example.projetopdm.CadastroActivity.validarTelefone;

import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.projetopdm.clinica.Agenda;
import com.example.projetopdm.usuarios.Usuario;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {

    EditText et_email, et_senha, et_telefone, et_cidade;
//    ImageButton edit_email, edit_senha, edit_telefone, edit_cidade;
    Button bt_atualizar;


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

        //Botoes para editar
//        edit_email = v.findViewById(R.id.editar_email);
//        edit_senha = v.findViewById(R.id.editar_senha);
//        edit_telefone = v.findViewById(R.id.editar_telefone);
//        edit_cidade = v.findViewById(R.id.editar_cidade);

        bt_atualizar = v.findViewById(R.id.atualizar);

        //Desabilitar os EditTexts
        disableAll();

        //Adicionando Listeners para os botoes de edição dos dados

//        edit_email.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ableEditText(et_email);
//            }
//        });
//
//        edit_senha.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ableEditText(et_senha);
//            }
//        });
//
//        edit_telefone.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ableEditText(et_telefone);
//            }
//        });
//
//        edit_cidade.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ableEditText(et_cidade);
//            }
//        });

        bt_atualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isDadosValidos()) {
                    disableAll();
                }
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

    public void disableEditText(EditText editText){
        editText.setEnabled(false);
    }

    public void ableEditText(EditText editText){
        editText.setEnabled(true);
    }

    public void disableAll (){
        disableEditText(et_email);
        disableEditText(et_senha);
        disableEditText(et_cidade);
        disableEditText(et_telefone);
    }

    public boolean isDadosValidos(){
        if(validarEmail(et_email) && validarSenha(et_senha) && validarTelefone(et_telefone) && validarCidade(et_cidade)){
            return true;
        }
        return false;
    }

}