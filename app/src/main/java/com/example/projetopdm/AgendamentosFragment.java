package com.example.projetopdm;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.database.Session;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.adaptador.AdaptadorRecyclerViewHorarios;

public class AgendamentosFragment extends Fragment {

    Button agendar_horario;
    public ImageView iconeExcluir, iconeEditar;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    RecyclerView recyclerView;
    AdaptadorRecyclerViewHorarios adaptador;
    View v;

    AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);

    private Session session;

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public AgendamentosFragment() {
    }

    public static AgendamentosFragment newInstance(String param1, String param2) {
        AgendamentosFragment fragment = new AgendamentosFragment();
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
        session = new Session(getActivity().getBaseContext());

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_agendamentos, container, false);

        agendar_horario = v.findViewById(R.id.criar_agendamento);

        agendar_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendarHorarioFragment()).commit();

            }
        });

        iconeEditar = v.findViewById(R.id.iconeEditar);
        iconeExcluir = v.findViewById(R.id.iconeExcluir);

        // Configuração RecyclerView
        recyclerView = v.findViewById(R.id.lstDados);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Valores e Adapter
        AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
        adaptador = new AdaptadorRecyclerViewHorarios(agendamentoRepo.buscarAgendamentosUsuario(session.getID()), conexao);
        recyclerView.setAdapter(adaptador);

        return v;
    }

    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getActivity());
            conexao = dadosOpenHelper.getWritableDatabase();

            agendamentoRepo = new AgendamentoRepo(conexao);

        } catch (SQLException ex) {
            androidx.appcompat.app.AlertDialog.Builder dlg = new AlertDialog.Builder(getActivity());
            dlg.setTitle(R.string.message_erro);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.message_ok, null);
            dlg.show();
        }
    }
}