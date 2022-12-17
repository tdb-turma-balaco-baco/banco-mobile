package com.example.finapp.view.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.finapp.R;

public class ClassificacaoHolder extends RecyclerView.ViewHolder {

    TextView txtOperacao;
    TextView txtValor;
    TextView txtClassificacao;

    public ClassificacaoHolder(@NonNull View itemView) {
        super(itemView);
        declareFields(itemView);
    }

    private void declareFields(@NonNull View itemView) {
        txtClassificacao = itemView.findViewById(R.id.c_txtClassificacao);
        txtValor = itemView.findViewById(R.id.c_txtValor);
        txtOperacao = itemView.findViewById(R.id.c_txtOperacao);
    }
}