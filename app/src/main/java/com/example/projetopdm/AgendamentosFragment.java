package com.example.projetopdm;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.database.Session;
import com.example.projetopdm.dominios.entidades.Usuarios;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;
import com.example.projetopdm.utilidadesadaptador.AdaptadorRecyclerViewHorarios;

public class AgendamentosFragment extends Fragment {

    Button agendar_horario;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    RecyclerView recyclerView;
    AdaptadorRecyclerViewHorarios adaptador;
    View view;

    AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
    UsuarioRepo usuarioRepo = new UsuarioRepo(conexao);

    Usuarios usuarioatual;

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

        view = inflater.inflate(R.layout.fragment_agendamentos, container, false);

        agendar_horario = view.findViewById(R.id.criar_agendamento);

        agendar_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendarHorarioFragment()).commit();

            }
        });

        /// interações
        /*adaptador.setEventoClicarNoIconeEditar(new MeuEventoDeClickDaLista<Procedimento>() {
            @Override
            public void onItemClick(Procedimento conteudoDaLinha) {
                // abre a outra tela para edição
                AgendarHorarioFragment fragment = new AgendarHorarioFragment();
                fragment.setProcedimento(conteudoDaLinha);

                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.activity_usuario, fragment).commit();
            }
        });*/

        /*adaptador.setEventoClicarNoIconeDeletar(new MeuEventoDeClickDaLista<Procedimento>() {
            @Override
            public void onItemClick(Procedimento conteudoDaLinha) {
                // Cria mensagem de alerta
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext()); // 2 - Estilo do Alerta
                // Adiciona texto do Título
                builder.setTitle("Exclusão de registro");
                // Adiciona mensagem do Alerta
                builder.setMessage("Você realmente deseja excluir esse registro?");
                // Adiciona botão, se null apenas fecha o alerta
                builder.setNegativeButton("Não", null);
                // adiciona botão, evento onClick adicionado
                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // exclui do banco de dados
                        //contatoDAO.excluir(contato);
                        // após excluir, listar dados atualizados
                        listarDados();
                        // mesagem de excluído com sucesso
                        Toast.makeText(getContext(),"Registro excluído sucesso!", Toast.LENGTH_SHORT).show();
                    }
                });
                // exibe o Alerta na tela
                builder.show();
            }
        });*/


        // Configuração RecyclerView
        recyclerView = view.findViewById(R.id.lstDados);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);

        // Valores e Adapter
        AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
        adaptador = new AdaptadorRecyclerViewHorarios(agendamentoRepo.buscarAgendamentosUsuario(session.getID()));
        // vincilar adaptador e RecyclerView
        recyclerView.setAdapter(adaptador);
        return view;
    }

    public void criarConexao() {
        try {
            dadosOpenHelper = new DadosOpenHelper(getActivity());

            conexao = dadosOpenHelper.getWritableDatabase();

            //Snackbar.make(activity_main, R.string.message_conexao_ok, Snackbar.LENGTH_LONG).setAction(R.string.message_ok, null).show();

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