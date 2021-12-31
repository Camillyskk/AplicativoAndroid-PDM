package com.example.projetopdm.utilidadesadaptador;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;

public class ModeloDeLinhaHorario extends RecyclerView.ViewHolder {
    // declaração
    public TextView nomeProcedimento;
    public TextView valor;
    public ImageView iconeExcluir;
    public ImageView iconeEditar;

    public ModeloDeLinhaHorario(View itemView) {
        super(itemView);
        // vinculações
        nomeProcedimento = itemView.findViewById(R.id.nome_procedimento);
        valor = itemView.findViewById(R.id.senha);
        iconeExcluir = itemView.findViewById(R.id.imageViewEditar);
        iconeEditar = itemView.findViewById(R.id.imageViewExcluir);
    }
}
