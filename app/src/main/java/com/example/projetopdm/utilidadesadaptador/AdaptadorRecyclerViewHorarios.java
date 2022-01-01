package com.example.projetopdm.utilidadesadaptador;

import static java.security.AccessController.getContext;
import static java.text.NumberFormat.getCurrencyInstance;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.database.DadosOpenHelper;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.repositorios.AgendamentoRepo;
import com.example.projetopdm.dominios.entidades.repositorios.ProcedimentoRepo;

import java.text.NumberFormat;
import java.util.List;

public class AdaptadorRecyclerViewHorarios extends RecyclerView.Adapter<HolderModeloDeLinhaHorario> {

    public final List<Agendamento> dados;

    public AdaptadorRecyclerViewHorarios(List<Agendamento> dados) {
        this.dados = dados;
    }


    public void adicionarAgendamento(Agendamento agendamento){
        dados.add(agendamento);
        notifyItemInserted(getItemCount());
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

        return holder;
    }

    @Override
    public void onBindViewHolder(HolderModeloDeLinhaHorario holder, int position) {

        NumberFormat priceFormat = NumberFormat.getCurrencyInstance();

        holder.procedimento.setText(String.valueOf(dados.get(position).procedimento));
        holder.data.setText(String.valueOf(dados.get(position).dia));
        holder.hora.setText(String.valueOf(dados.get(position).hora));
        holder.valor.setText(String.valueOf(priceFormat.format(dados.get(position).valor)));
    }

    @Override
    public int getItemCount() {
        return dados != null ? dados.size() : 0;
    }

}
