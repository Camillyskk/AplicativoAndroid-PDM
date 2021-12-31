package com.example.projetopdm;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.ProcedimentoRepo;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgendarHorarioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgendarHorarioFragment extends Fragment {

    private Procedimento procedimento;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
    ProcedimentoRepo procedimentoRepo = new ProcedimentoRepo(conexao);

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AgendarHorarioFragment() {
        // Required empty public constructor
    }

    public static AgendarHorarioFragment newInstance(String param1, String param2) {
        AgendarHorarioFragment fragment = new AgendarHorarioFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        criarConexao();
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        };


    public void preencheSpinner(Procedimento procedimento){
        String nome = procedimento.nome;

        List<Procedimento> lista = procedimentoRepo.buscarProcedimentos();

        //ArrayAdapter<String> adapter_spinner = ArrayAdapter.createFromResource(getContext(), lista, R.layout.spinner_item);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_agendar_horario, container, false);

        //spinner lista procedimentos
        Spinner spinner = (Spinner) v.findViewById(R.id.select_procedimento);
        //ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.procedimentos, R.layout.spinner_item);
        ArrayAdapter spinner_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, procedimentoRepo.spinnerProcedimentos());
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinner_adapter);

        //spinner lista horarios
        Spinner spinner2 = (Spinner) v.findViewById(R.id.select_horario);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.horarios, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);

        return v;
    }


    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getActivity());

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(activity_main, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

            agendamentoRepo = new AgendamentoRepo(conexao);
            procedimentoRepo = new ProcedimentoRepo(conexao);

        } catch (SQLException ex) {
            androidx.appcompat.app.AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
    }

}