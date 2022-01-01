package com.example.projetopdm.utilidadesadaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.dominios.entidades.Agendamento;
import com.example.projetopdm.dominios.entidades.Usuarios;

import java.util.List;

public class TesteAdapter extends RecyclerView.Adapter<TesteAdapter.HolderModeloDeLinha> {

    public List<Agendamento> dados;

    public TesteAdapter (List<Agendamento> dados){
        this.dados = dados;
    }

    @Override
    public TesteAdapter.HolderModeloDeLinha onCreateViewHolder(ViewGroup parent, int viewType) { //pega o modelo de linha

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //referencia layout

        View view = layoutInflater.inflate(R.layout.linha, parent, false); //referencia linha

        HolderModeloDeLinha holderteste = new HolderModeloDeLinha(view);

        return holderteste;
    }

    @Override
    public void onBindViewHolder(HolderModeloDeLinha holder, int position) { //passa dado do banco pro elemento

        if(dados != null && dados.size() > 0){
            Agendamento agendamento = dados.get(position);

            //nome no ViewHolder      campo no banco
            holder.data.setText(agendamento.dia);
            holder.hora.setText(agendamento.hora);
            holder.procedimento.setText(agendamento.procedimento_id);
            //holder.valor.setText(agendamento.valor);
        }

    }

    @Override
    public int getItemCount() {
        return dados.size();
    }

    class ViewHolderTeste extends RecyclerView.ViewHolder{ //vincula aos elementos

        public TextView rvemail, rvsenha; //ids dos tv

        public ViewHolderTeste(View itemView){
            super(itemView);

            rvemail = itemView.findViewById(R.id.email);
            rvsenha = itemView.findViewById(R.id.valor);

        }
    }
    public class HolderModeloDeLinha extends RecyclerView.ViewHolder {
        public TextView procedimento, valor, hora, data;
        public ImageView iconeExcluir, iconeEditar;

        public HolderModeloDeLinha(View itemView) {
            super(itemView);
            procedimento = itemView.findViewById(R.id.nome_procedimento);
            valor = itemView.findViewById(R.id.valor);
            hora = itemView.findViewById(R.id.hora);
            data = itemView.findViewById(R.id.data);
            iconeExcluir = itemView.findViewById(R.id.iconeExcluir);
            iconeEditar = itemView.findViewById(R.id.iconeEditar);
        }
    }
}
