package com.example.projetopdm;

import android.content.DialogInterface;
import android.content.Intent;
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
import android.widget.Toast;

import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.UsuarioRepo;
import com.example.projetopdm.utilidadesadaptador.AdaptadorRecyclerViewHorarios;
import com.example.projetopdm.utilidadesadaptador.MeuEventoDeClickDaLista;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AgendamentosFragment extends Fragment {
    Button agendar_horario;

    static SQLiteDatabase conexao;
    static DadosOpenHelper dadosOpenHelper;

    AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);

    // Declarações relacionadas com o RecyclerCiew
    RecyclerView recyclerView;
    AdaptadorRecyclerViewHorarios adaptador;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        criarConexao();
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_agendamentos, container, false);

        agendar_horario = view.findViewById(R.id.criar_agendamento);

        agendar_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendarHorarioFragment()).commit();

            }
        });

        /*
        // vincular objetos
        recyclerView = view.findViewById(R.id.lstDados);

        // configuro o RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        // Valores e Adapter
        adaptador = new AdaptadorRecyclerViewHorarios();
        // vincilar adaptador e RecyclerView
        recyclerView.setAdapter(adaptador);

        /// interações
        adaptador.setEventoClicarNoIconeEditar(new MeuEventoDeClickDaLista<Procedimento>() {
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

        configurarRecycler();

        /*
        Intent i = new Intent(getActivity().getBaseContext(), UsuarioActivity.class);
        getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendamentosFragment()).commit();
        */

        return view;
    }



    void configurarRecycler(){
        // Configurando o gerenciador de layout para ser uma lista.
        recyclerView = view.findViewById(R.id.lstDados);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(layoutManager);

        // Adiciona o adapter que irá anexar os objetos à lista.
        AgendamentoRepo agendamentoRepo = new AgendamentoRepo(conexao);
        adaptador = new AdaptadorRecyclerViewHorarios(agendamentoRepo.buscarAgendamentos());
        recyclerView.setAdapter(adaptador);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity().getBaseContext(), DividerItemDecoration.VERTICAL));
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