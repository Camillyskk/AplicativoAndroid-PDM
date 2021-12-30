package com.example.projetopdm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.utilidadesadaptador.AdaptadorRecyclerViewHorarios;
import com.example.projetopdm.utilidadesadaptador.MeuEventoDeClickDaLista;

/**
 * A simple {@link Fragment} subclass.
 * create an instance of this fragment.
 */
public class AgendamentosFragment extends Fragment {
    Button agendar_horario;

    // Declarações relacionadas com o RecyclerCiew
    private RecyclerView recyclerView;
    private AdaptadorRecyclerViewHorarios adaptador;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_agendamentos, container, false);

        agendar_horario = view.findViewById(R.id.criar_agendamento);

        agendar_horario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendarHorarioFragment()).commit();

            }
        });

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
        });

        adaptador.setEventoClicarNoIconeDeletar(new MeuEventoDeClickDaLista<Procedimento>() {
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
        });


        return view;
    }

    private void listarDados() {
        adaptador.getDados().clear();

        Procedimento p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p);

         p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p);
        p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p); p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p); p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p); p = new Procedimento();
        p.nome = "Massagem";
        p.valor = 4.85;
        //adaptador.getDados().addAll()
        adaptador.getDados().add(p);

        adaptador.notifyDataSetChanged();
    }


    @Override
    public void onResume() {
        super.onResume();
        // adiciona dados
        listarDados();
    }
}