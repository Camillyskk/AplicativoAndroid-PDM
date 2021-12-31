package com.example.projetopdm.utilidadesadaptador;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Procedimento;
import com.example.projetopdm.dominios.entidades.Usuarios;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

public class AdaptadorRecyclerViewHorarios extends RecyclerView.Adapter<ModeloDeLinhaHorario> {
    // declarações

    public List<Agendamento> dados;

    public AdaptadorRecyclerViewHorarios(){
        this.dados = dados;
    }

    //private final List<Procedimento> dadosDoRecyclerView;
    private Context ativityEmExecucao; // (Opcional) Usado em métodos para acesso a tela

    /*public AdaptadorRecyclerViewHorarios() {
        dados = new ArrayList<>();
    }*/

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
    }


    @Override
    public ModeloDeLinhaHorario onCreateViewHolder(ViewGroup parent, int viewType) {
        // pega o contexto (opcional)
        ativityEmExecucao = parent.getContext();
        // instanciar a linha usando o LayoutInflater
        ModeloDeLinhaHorario holder=new ModeloDeLinhaHorario(LayoutInflater.from(ativityEmExecucao)
                .inflate(R.layout.modelo_de_linha_horarios_cliente, parent, false));
        // adicionar eventos para interagir com a linha
        if(eventoClicarNoIconeDeletar != null){
            holder.iconeExcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Agendamento conteudo = getDados().get(holder.getAdapterPosition());
                    // executar o evento
                    eventoClicarNoIconeDeletar.onItemClick(conteudo);
                }
            });
        }
        if(eventoClicarNoIconeEditar != null){
            holder.iconeEditar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Agendamento conteudo = getDados().get(holder.getAdapterPosition());
                    // executar o evento
                    eventoClicarNoIconeEditar.onItemClick(conteudo);
                }
            });
        }

        // retorno o modelo de linha instanciado
        return holder;
    }

    @Override
    public void onBindViewHolder(ModeloDeLinhaHorario modeloDeLinha, int position) {

        if(dados != null && dados.size() > 0){
            //Usuarios usuario = dados.get(position);

            //nome no ViewHolder      campo no banco
            //holder.rvemail.setText(usuario.email);
            //holder.rvsenha.setText(usuario.senha);
        }

        // pego o dado que vai ser exibido
        Procedimento conteudo = dados.get(position);
        // exibir o dado
        modeloDeLinha.nomeProcedimento.setText(conteudo.nome);
        modeloDeLinha.valor.setText(NumberFormat.getCurrencyInstance().format(conteudo.valor));

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }
}
