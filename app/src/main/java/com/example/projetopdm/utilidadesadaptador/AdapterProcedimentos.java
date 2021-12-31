package com.example.projetopdm.utilidadesadaptador;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.Procedimento;

import java.util.List;

public class AdapterProcedimentos extends RecyclerView.Adapter<HolderModeloDeLinhaHorario> {


    public final List<Procedimento> dados;

    public AdapterProcedimentos(List<Procedimento> dados) {
        this.dados = dados;
    }


    public void adicionarProcedimento(Procedimento procedimento){
        dados.add(procedimento);
        notifyItemInserted(getItemCount());
    }


    /*private Context ativityEmExecucao; // (Opcional) Usado em métodos para acesso a tela

    // usado para adicionar e remover dados
    public List<Procedimento> getDados() {
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
        return new HolderModeloDeLinhaHorario(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modelo_de_linha_horarios_cliente, parent, false));
    }

    @Override
    public void onBindViewHolder(HolderModeloDeLinhaHorario holder, int position) {
        holder.procedimento.setText(dados.get(position).nome);
    }

    @Override
    public int getItemCount() {
        return dados != null ? dados.size() : 0;
    }
}
