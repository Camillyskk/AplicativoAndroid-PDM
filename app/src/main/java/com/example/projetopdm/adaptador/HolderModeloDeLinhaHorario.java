package com.example.projetopdm.adaptador;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;

public class HolderModeloDeLinhaHorario extends RecyclerView.ViewHolder {
    public TextView procedimento, valor, hora, data;
    public ImageView iconeExcluir, iconeEditar;

    public HolderModeloDeLinhaHorario(View itemView) {
        super(itemView);
        procedimento = itemView.findViewById(R.id.nome_procedimento);
        valor = itemView.findViewById(R.id.valor);
        hora = itemView.findViewById(R.id.hora);
        data = itemView.findViewById(R.id.data);
        iconeExcluir = itemView.findViewById(R.id.iconeExcluir);
        iconeEditar = itemView.findViewById(R.id.iconeEditar);
    }
}
