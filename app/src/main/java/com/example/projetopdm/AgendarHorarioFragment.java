package com.example.projetopdm;

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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.ProcedimentoRepo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.List;

public class AgendarHorarioFragment extends Fragment {

    Button novo_agendamento;
    EditText select_data;

    private Procedimento procedimento;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
    ProcedimentoRepo procedimentoRepo = new ProcedimentoRepo(conexao);

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AgendarHorarioFragment() {
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

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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

        novo_agendamento = v.findViewById(R.id.novo_agendamento);
        select_data = v.findViewById(R.id.select_data);

        novo_agendamento.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {

                if (validarDataNasc(select_data)){
                    Agendamento agendamento = new Agendamento();
                    Procedimento procedimento = new Procedimento();

                    String nome = spinner.getSelectedItem().toString();

                    procedimento = procedimentoRepo.buscarProcedimentoNome(nome);

                    agendamento.procedimento_id = procedimento.ID;
                    agendamento.usuarios_id = MainActivity.usuarioatual.ID;
                    agendamento.dia = String.valueOf(select_data.getText());
                    agendamento.hora = spinner2.getSelectedItem().toString();
                    agendamento.procedimento = (String.valueOf(procedimentoRepo.buscarProcedimentoID(procedimento.ID).nome));
                    agendamento.valor = (Double.valueOf(procedimentoRepo.buscarProcedimentoID(procedimento.ID).valor));
                    agendamentoRepo.inserir(agendamento);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendamentosFragment()).commit();
                }
            }
        });

        return v;
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
            editText.setError("Digite uma data válida.");
            return false;
        }
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