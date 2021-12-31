package com.example.projetopdm.utilidadesadaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.projetopdm.R;
import com.example.projetopdm.dominios.entidades.Usuarios;

import java.util.List;

public class TesteAdapter extends RecyclerView.Adapter<TesteAdapter.ViewHolderTeste> {

    public List<Usuarios> dados;

    public TesteAdapter (List<Usuarios> dados){
        this.dados = dados;
    }

    @Override
    public TesteAdapter.ViewHolderTeste onCreateViewHolder(ViewGroup parent, int viewType) { //pega o modelo de linha

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext()); //referencia layout

        View view = layoutInflater.inflate(R.layout.linha, parent, false); //referencia linha

        ViewHolderTeste holderteste = new ViewHolderTeste(view);

        return holderteste;
    }

    @Override
    public void onBindViewHolder(TesteAdapter.ViewHolderTeste holder, int position) { //passa dado do banco pro elemento

        if(dados != null && dados.size() > 0){
            Usuarios usuario = dados.get(position);

            //nome no ViewHolder      campo no banco
            holder.rvemail.setText(usuario.email);
            holder.rvsenha.setText(usuario.senha);
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
            rvsenha = itemView.findViewById(R.id.senha);

        }
    }
}
