package com.example.projetopdm.adaptador;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.UsuarioActivity;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;

import java.text.NumberFormat;
import java.util.List;

public class AdaptadorRecyclerViewHorarios extends RecyclerView.Adapter<HolderModeloDeLinhaHorario> {

    public final List<Agendamento> dados;

    static SQLiteDatabase conexao;
    AgendamentoRepo agendamentoRepo;

    static boolean editar = false;

    public AdaptadorRecyclerViewHorarios(List<Agendamento> dados, SQLiteDatabase conexao) {
        this.dados = dados;
        AdaptadorRecyclerViewHorarios.conexao = conexao;
    }

    @NonNull
    @Override
    public HolderModeloDeLinhaHorario onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.modelo_de_linha_horarios_cliente, parent, false);

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
                agendamentoRepo.excluir(getSelecao(position).ID);
                dados.remove(position);
                Toast.makeText(v.getContext(), "Registro excluído sucesso!", Toast.LENGTH_SHORT).show();
                notifyDataSetChanged();
            }
        });

        holder.iconeEditar.setOnClickListener(new ImageView.OnClickListener() {
            @Override
            public void onClick(View v) {
                editar = true;
                PassaDados.setAgendamento(getSelecao(position));

                Intent i = new Intent(v.getContext(), UsuarioActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dados != null ? dados.size() : 0;
    }

    public Agendamento getSelecao(int position){
        return dados.get(position);
    }

    public static boolean isEditar() {
        return editar;
    }

    public static void setEditar(boolean editar) {
        AdaptadorRecyclerViewHorarios.editar = editar;
    }
}
