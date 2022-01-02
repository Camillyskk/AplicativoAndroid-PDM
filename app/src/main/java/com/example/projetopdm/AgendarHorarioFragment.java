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
import android.widget.Toast;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.database.Session;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.ProcedimentoRepo;
import com.example.projetopdm.adaptador.AdaptadorRecyclerViewHorarios;
import com.example.projetopdm.adaptador.PassaDados;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class AgendarHorarioFragment extends Fragment {

    Button novo_agendamento;
    EditText select_data;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    AgendamentoRepo agendamentoRepo;
    ProcedimentoRepo procedimentoRepo = new ProcedimentoRepo(conexao);

    private Session session;

    Agendamento agendamentoEditado = null;

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
        session = new Session(getContext());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_agendar_horario, container, false);

        //spinner lista procedimentos
        Spinner spinner = v.findViewById(R.id.select_procedimento);
        ArrayAdapter spinner_adapter = new ArrayAdapter<>(getContext(), R.layout.spinner_item, procedimentoRepo.spinnerProcedimentos());
        spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(spinner_adapter);

        //spinner lista horarios
        Spinner spinner2 = v.findViewById(R.id.select_horario);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.horarios, R.layout.spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner2.setAdapter(adapter2);

        novo_agendamento = v.findViewById(R.id.novo_agendamento);
        select_data = v.findViewById(R.id.select_data);


        if (AdaptadorRecyclerViewHorarios.isEditar()){
            agendamentoEditado = PassaDados.getAgendamento();

            //vincula aos elementos da activity
            Spinner proce = v.findViewById(R.id.select_procedimento);
            EditText select_data = v.findViewById(R.id.select_data);
            Spinner select_horario = v.findViewById(R.id.select_horario);

            //seta os valores pra edição com o agendamento já existente
            select_data.setText(agendamentoEditado.dia);
            proce.setSelection(getIndex(proce, agendamentoEditado.procedimento));
            select_horario.setSelection(getIndex(select_horario, agendamentoEditado.hora));
        }

        novo_agendamento.setOnClickListener(new View.OnClickListener(){
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                if (validarDataNasc(select_data)){
                    Agendamento agendamento = new Agendamento();
                    Procedimento procedimento;

                    agendamentoRepo = new AgendamentoRepo(conexao);

                    String nome = spinner.getSelectedItem().toString();

                    procedimento = procedimentoRepo.buscarProcedimentoNome(nome);

                    agendamento.procedimento_id = procedimento.ID;
                    agendamento.usuario_id = session.getID();
                    agendamento.dia = String.valueOf(select_data.getText());
                    agendamento.hora = spinner2.getSelectedItem().toString();
                    agendamento.procedimento = String.valueOf(procedimentoRepo.buscarProcedimentoID(procedimento.ID).nome);
                    agendamento.valor = procedimentoRepo.buscarProcedimentoID(procedimento.ID).valor;

                    if (AdaptadorRecyclerViewHorarios.isEditar()){
                        agendamento.ID = agendamentoEditado.ID;
                        agendamentoRepo.alterar(agendamento);

                        Toast.makeText(getContext(),"Agendamento alterado com sucesso!", Toast.LENGTH_SHORT).show();
                        AdaptadorRecyclerViewHorarios.setEditar(false);
                    }else{
                        agendamentoRepo.inserir(agendamento);
                    }

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

    //usado pra setar a posição inicial do spinner
    private int getIndex(Spinner spinner, String myString)
    {
        int index = 0;
        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
}