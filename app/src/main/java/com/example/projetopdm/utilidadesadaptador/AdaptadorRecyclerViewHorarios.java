package com.example.projetopdm.utilidadesadaptador;

import static android.app.PendingIntent.getActivity;
import static java.security.AccessController.getContext;
import static java.text.NumberFormat.getCurrencyInstance;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.AgendamentosFragment;
import com.example.projetopdm.AgendarHorarioFragment;
import com.example.projetopdm.R;
import com.example.projetopdm.database.Conexao;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;

import java.text.NumberFormat;
import java.util.List;

public class AdaptadorRecyclerViewHorarios extends RecyclerView.Adapter<HolderModeloDeLinhaHorario> {

    public final List<Agendamento> dados;

    static SQLiteDatabase conexao;
    AgendamentoRepo agendamentoRepo;

    public AdaptadorRecyclerViewHorarios(List<Agendamento> dados, SQLiteDatabase conexao) {
        this.dados = dados;
        this.conexao = conexao;
    }

    /*private Context ativityEmExecucao; // (Opcional) Usado em métodos para acesso a tela

    // usado para adicionar e remover dados
    public List<Agendamento> getDados() {
        return dados;
    }

    // declarar os eventos que vou usar na minha lista
    public MeuEventoDeClickDaLista<Procedimento> eventoClicarNoIconeDeletar = null;
    public MeuEventoDeClickDaLista<Procedimento> eventoClicarNoIconeEditar = null;

    // evento adicionar evento de click na linha

    public void setEventoClicarNoIconeDeletar(MeuEventoDeClickDaLista<Procedimento> evento) {
        this.eventoClicarNoIconeDeletar = evento;
    }

    public void setEventoClicarNoIconeEditar(MeuEventoDeClickDaLista<Procedimento> evento) {
        this.eventoClicarNoIconeEditar = evento;
    }*/


    @Override
    public HolderModeloDeLinhaHorario onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //referencia layout

        View view = layoutInflater.inflate(R.layout.modelo_de_linha_horarios_cliente, parent, false); //referencia linha

        HolderModeloDeLinhaHorario holder = new HolderModeloDeLinhaHorario(view);

        agendamentoRepo = new AgendamentoRepo(conexao);

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderModeloDeLinhaHorario holder, int position) {

        NumberFormat priceFormat = NumberFormat.getCurrencyInstance();

        holder.procedimento.setText(String.valueOf(dados.get(position).procedimento));
        holder.data.setText(String.valueOf(dados.get(position).dia));
        holder.hora.setText(String.valueOf(dados.get(position).hora));
        holder.valor.setText(String.valueOf(priceFormat.format(dados.get(position).valor)));

        holder.iconeExcluir.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("ID: ", String.valueOf(getItemId(position)));
                Log.d("AGENDAMENTO: ", String.valueOf(agendamentoRepo.buscarAgendamento((int) getItemId(position)).procedimento));
                agendamentoRepo.excluir((int) getItemId(position));
                dados.remove(position);
                Toast.makeText(v.getContext(), "Registro excluído sucesso!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
                //getActivity().getIntent().getSupportFragmentManager().beginTransaction().replace(R.id.activity_usuario, new AgendarHorarioFragment()).commit();

               /*Intent intent = getActivity(v).getIntent();
                Bundle bundle = new Bundle();
                bundle.putSerializable("agendamento", agendamento);
                intent.putExtras(bundle);
                Agendamento agendamentoEditado = (Agendamento) bundle.getSerializable("agendamento");
                Log.d("ID AGENDAMENTO: ", String.valueOf(agendamentoEditado.ID));
                */
                //fragment.setArguments(bundle);
                //Activity activity = getActivity(v);
                //Intent intent = activity.getIntent();
                //intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                //intent.putExtra("agendamento", (Serializable) agendamento);
                //activity.finish();
                //activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados != null ? dados.size() : 0;
    }

    public long getItemId(int position) {
        return dados.get(position).ID;
    }

}
